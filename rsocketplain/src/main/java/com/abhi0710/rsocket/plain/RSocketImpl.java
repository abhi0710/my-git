package com.abhi0710.rsocket.plain;

import com.abhi0710.rsocket.plain.request.FileRequest;
import io.rsocket.AbstractRSocket;
import io.rsocket.Payload;
import io.rsocket.ResponderRSocket;
import io.rsocket.util.DefaultPayload;
import reactor.core.publisher.Mono;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.time.Instant;

import com.fasterxml.jackson.databind.ObjectMapper;

public class RSocketImpl extends AbstractRSocket implements ResponderRSocket {

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Mono<Payload> requestResponse(Payload payload) {

        String str = payload.getDataUtf8();
        System.out.println(payload.getMetadataUtf8());
        payload.release();


        FileRequest request = null;
        try {
            request = objectMapper.readValue(str, FileRequest.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(Instant.now());
            System.out.println("Recieved Request on therad " + Thread.currentThread().getName());

            System.out.println(Runtime.getRuntime().totalMemory());
            System.out.println("free " + Runtime.getRuntime().freeMemory());


            try (RandomAccessFile file = new
                    RandomAccessFile(request.getFileName(), "r")) {


                FileChannel channel = file.getChannel();

                if (request.getBlock() == -1)

                    return Mono.just(DefaultPayload.create(channel.map(
                            FileChannel.MapMode.READ_ONLY, 0, channel.size()
                    )));

                else {

                    long startBlock = request.getBlock() * 1024 * 1024;
                    long endBlock = startBlock + (1024 * 1024) - 1;

                    long size = 1024 * 1024;

                    if (endBlock > channel.size())
                        size = channel.size() - startBlock;

                    System.out.println("Block " + request.getBlock() + " from "  + startBlock
                    + " to " + endBlock + " fileChannel " + channel.size());

                    if (channel.size() <  startBlock)
                        return Mono.just(DefaultPayload.create(ByteBuffer.allocate(0)));

                    if (channel.size() < endBlock) {
                        MappedByteBuffer byteBuffer = channel.map(
                                FileChannel.MapMode.READ_ONLY, startBlock ,
                                size
                        );
                        return Mono.just(DefaultPayload.create(byteBuffer));
                    } else {
                        MappedByteBuffer byteBuffer = channel.map(
                                FileChannel.MapMode.READ_ONLY, startBlock , size
                        );
                        return Mono.just(DefaultPayload.create(byteBuffer));
                    }
                }
            } catch (Exception e) {
                System.out.println("Failed " + request.getBlock());
                e.printStackTrace();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return Mono.just(DefaultPayload.create("Hello"));
    }
}
