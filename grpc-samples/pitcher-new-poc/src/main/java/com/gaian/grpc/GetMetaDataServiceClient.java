package com.gaian.grpc;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 * User: Naresh.P (GSIHYD-1298)
 * Date: 31/5/19
 * Time: 10:51 AM
 */
public class GetMetaDataServiceClient {

    public static void main(String[] args) {


        ExecutorService executorService = Executors.newFixedThreadPool(200);

        List<Callable<Object>> downloadThreadList=new ArrayList<>();

        Instant start = Instant.now();


        for (int index = 1; index <= 100; index++) {
            downloadThreadList.add(new ChunkDownloadThread("/split6", "/home/gaian/uploads/converted" + index + "/"));
        }

        try {
            executorService.invokeAll(downloadThreadList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Instant finish = Instant.now();

        long timeElapsed = Duration.between(start, finish).toMillis();

        long minutes = TimeUnit.MILLISECONDS.toSeconds(timeElapsed);

        System.out.println("total time elapsed = " + minutes);

        executorService.shutdown();


        /*ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 6565)
                .usePlaintext()
                .maxInboundMessageSize(1073741824)
                .build();

        GetMetaDataServiceGrpc.GetMetaDataServiceBlockingStub stub = GetMetaDataServiceGrpc.newBlockingStub(channel);
        GetChunkServiceGrpc.GetChunkServiceBlockingStub chunkStub = GetChunkServiceGrpc.newBlockingStub(channel);

        GetMetaDataRequest request = GetMetaDataRequest.newBuilder().setFilePath("/split3").build();

        GetMetaDataResponse response = stub.getMetaData(request);

        List<File> files = new ArrayList<>();

        response.getFilesList().parallelStream().forEach(file -> {


            GetChunkRequest chunkRequest = GetChunkRequest.newBuilder().setFilePath(file).build();

            GetChunkResponse chunkResponse = chunkStub.getChunk(chunkRequest);


//            System.out.println("chunkResponse = " + chunkResponse);
            //File tempFile = new File("/home/gaian/uploads/converted/" + Paths.get(file).toFile().getName() );
            try {
                FileUtils.writeByteArrayToFile(new File("/home/gaian/uploads/converted/" + Paths.get(file).toFile().getName() ),chunkResponse.getData().toByteArray());
            } catch (IOException e) {
                e.printStackTrace();
            }

            //files.add(tempFile);


            return;

        });


        channel.shutdown();*/

    }

}
