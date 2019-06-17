package com.gaian.grpc;

import com.gaian.service.FileStorageService;
import com.google.protobuf.ByteString;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by IntelliJ IDEA.
 * User: Naresh.P (GSIHYD-1298)
 * Date: 31/5/19
 * Time: 11:06 AM
 */
@GRpcService
@Slf4j
public class GetChunkServiceImpl extends GetChunkServiceGrpc.GetChunkServiceImplBase {


    public void getChunk(GetChunkRequest request, StreamObserver<GetChunkResponse> responseObserver)
    {
        if (request.getFilePath().contains("6"))
            getChunkOld(request, responseObserver);
        else
            getChunkNew(request, responseObserver);
    }
    public void getChunkOld(GetChunkRequest request, StreamObserver<GetChunkResponse> responseObserver) {

        String filePath = request.getFilePath();

        log.info("Given File Path : {}",filePath);

        byte[] bytes=null;
        try {
            bytes = Files.readAllBytes(Paths.get(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        GetChunkResponse response = GetChunkResponse.newBuilder().
                setData(ByteString.copyFrom(bytes)).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }


    public void getChunkNew(GetChunkRequest request, StreamObserver<GetChunkResponse> responseObserver) {

        String filePath = request.getFilePath();

        RandomAccessFile aFile     = null;
        try {
            aFile = new RandomAccessFile(request.getFilePath(), "r");

        FileChannel channel = aFile.getChannel();
        MappedByteBuffer buffer =        channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());

        log.info("Given File Path : {}",filePath);

        GetChunkResponse response = GetChunkResponse.newBuilder().
                setData(ByteString.copyFrom(buffer)).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
