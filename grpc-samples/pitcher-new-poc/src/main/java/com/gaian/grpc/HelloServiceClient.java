package com.gaian.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by IntelliJ IDEA.
 * User: Naresh.P (GSIHYD-1298)
 * Date: 31/5/19
 * Time: 7:14 AM
 */
//@Component
public class HelloServiceClient {

    private HelloServiceGrpc.HelloServiceBlockingStub helloServiceBlockingStub;

    /*@PostConstruct
    private void init() {
        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress("localhost", 6565).usePlaintext().build();

        helloServiceBlockingStub =
                HelloServiceGrpc.newBlockingStub(managedChannel);
    }*/

    public String hello(String firstName, String lastName) {
        HelloRequest request = HelloRequest.newBuilder().setFirstName(firstName)
                .setLastName(lastName).build();

        HelloResponse response =
                helloServiceBlockingStub.hello(request);

        return response.getGreeting();
    }

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();

        HelloServiceGrpc.HelloServiceBlockingStub stub
                = HelloServiceGrpc.newBlockingStub(channel);

        System.out.println("Request Initiating");
        HelloResponse helloResponse = stub.hello(HelloRequest.newBuilder()
                .setFirstName("Naresh")
                .setLastName("P")
                .build());
        System.out.println("Response : " + helloResponse.getGreeting());
        channel.shutdown();
    }

}
