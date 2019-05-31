package com.gaian.grpc;

import com.gaian.service.FileStorageService;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Naresh.P (GSIHYD-1298)
 * Date: 31/5/19
 * Time: 10:39 AM
 */
@GRpcService
@Slf4j
public class GetMetaDataServiceImpl extends GetMetaDataServiceGrpc.GetMetaDataServiceImplBase {

    @Autowired
    private FileStorageService fileStorageService;

    @Override
    public void getMetaData(GetMetaDataRequest request, StreamObserver<GetMetaDataResponse> responseObserver) {

        String filePath = request.getFilePath();

        log.info("Given File Path : {}",filePath);

        List<String> files=null;

        try {
            files = fileStorageService.listFiles(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        GetMetaDataResponse response = GetMetaDataResponse.newBuilder()
                .addAllFiles(files).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }
}
