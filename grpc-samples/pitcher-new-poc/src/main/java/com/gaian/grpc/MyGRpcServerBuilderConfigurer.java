package com.gaian.grpc;

import io.grpc.ServerBuilder;
import io.grpc.netty.NettyServerBuilder;
import org.lognet.springboot.grpc.GRpcServerBuilderConfigurer;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;

/**
 * Created by IntelliJ IDEA.
 * User: Naresh.P (GSIHYD-1298)
 * Date: 31/5/19
 * Time: 4:17 PM
 */
@Component
public class MyGRpcServerBuilderConfigurer extends GRpcServerBuilderConfigurer {
    @Override
    public void configure(ServerBuilder<?> serverBuilder){
        serverBuilder
                .executor(Executors.newFixedThreadPool(500));

    }

}