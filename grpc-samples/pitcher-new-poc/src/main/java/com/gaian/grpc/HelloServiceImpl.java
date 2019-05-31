package com.gaian.grpc;

import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

/**
 * Created by IntelliJ IDEA.
 * User: Naresh.P (GSIHYD-1298)
 * Date: 31/5/19
 * Time: 7:10 AM
 */
@GRpcService
public class HelloServiceImpl extends  HelloServiceGrpc.HelloServiceImplBase{

    @Override
    public void hello(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {

        System.out.println("Got Request : " + request.getFirstName() + " "  + request.getLastName());

        HelloResponse response = HelloResponse.newBuilder().setGreeting(request.getFirstName() + " " + request.getLastName()).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
        System.out.println("Request Completed");
    }
}
