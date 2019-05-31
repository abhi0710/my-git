package com.gaian.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Callable;

/**
 * Created by IntelliJ IDEA.
 * User: Naresh.P (GSIHYD-1298)
 * Date: 31/5/19
 * Time: 12:34 PM
 */
public class ChunkDownloadThread implements Callable {


    private String filePath;
    private String downloadPath;

    public ChunkDownloadThread(String filePath, String downloadPath) {
        this.filePath = filePath;
        this.downloadPath = downloadPath;
    }


    @Override
    public Object call() throws Exception {

        ManagedChannel channel = ManagedChannelBuilder.forAddress("192.168.25.167", 6565)
                .usePlaintext()
                .maxInboundMessageSize(1073741824)
                .build();

        GetMetaDataServiceGrpc.GetMetaDataServiceBlockingStub stub = GetMetaDataServiceGrpc.newBlockingStub(channel);
        GetChunkServiceGrpc.GetChunkServiceBlockingStub chunkStub = GetChunkServiceGrpc.newBlockingStub(channel);

        GetMetaDataRequest request = GetMetaDataRequest.newBuilder().setFilePath(this.filePath).build();

        GetMetaDataResponse response = stub.getMetaData(request);

        try {
            Files.createDirectories(Paths.get(this.downloadPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        response.getFilesList().parallelStream().forEach(file -> {

            GetChunkRequest chunkRequest = GetChunkRequest.newBuilder().setFilePath(file).build();

            GetChunkResponse chunkResponse = chunkStub.getChunk(chunkRequest);

            try {
                FileUtils.writeByteArrayToFile(new File(this.downloadPath + Paths.get(file).toFile().getName() ),chunkResponse.getData().toByteArray());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        channel.shutdown();

        return null;

    }
}
