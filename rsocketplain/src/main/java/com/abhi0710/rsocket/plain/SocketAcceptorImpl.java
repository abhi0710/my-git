package com.abhi0710.rsocket.plain;

import io.rsocket.*;
import io.rsocket.util.DefaultPayload;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.time.Instant;
import java.util.concurrent.Flow;

public class SocketAcceptorImpl implements SocketAcceptor {

    @Override
    public Mono<RSocket> accept(ConnectionSetupPayload setupPayload, RSocket reactiveSocket) {
        return Mono.just(
                new AbstractRSocket() {

                    @Override
                    public Mono<Void> fireAndForget(Payload payload) {
                        //                  System.out.println(payload.getDataUtf8());
                        payload.release();
                        return Mono.empty();
                    }

                    @Override
                    public Mono<Payload> requestResponse(Payload payload) {
                        String str = payload.getDataUtf8();
                        payload.release();

                        System.out.println(str);

                        try {
                            System.out.println(Instant.now());
                            System.out.println("Recieved Request on therad " + Thread.currentThread().getName());

                            System.out.println(Runtime.getRuntime().totalMemory());
                            System.out.println("free " + Runtime.getRuntime().freeMemory());


                            RandomAccessFile file = new
                                    RandomAccessFile("/home/gaian/uploads/split6/Avatar Brochure Mail_01082018.pdf", "r");

                            System.out.println(str);

                            FileChannel channel = file.getChannel();
                            MappedByteBuffer byteBuffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
                            System.out.println(str);

                            file.close();
                            channel.close();

                            System.out.println(str);


                            return Mono.just(DefaultPayload.create(byteBuffer));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        return Mono.just(payload);
                    }
                });
    }
}

