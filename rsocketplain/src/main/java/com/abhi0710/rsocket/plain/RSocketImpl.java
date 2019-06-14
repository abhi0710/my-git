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



        try {
            System.out.println(objectMapper.readValue(str, FileRequest.class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(Instant.now());
                    System.out.println("Recieved Request on therad " + Thread.currentThread().getName());

            System.out.println(Runtime.getRuntime().totalMemory());
            System.out.println("free " + Runtime.getRuntime().freeMemory());


            RandomAccessFile file = new
                    RandomAccessFile("/home/gaian/uploads/split6/Avatar Brochure Mail_01082018.pdf", "r");


            FileChannel channel = file.getChannel();
            MappedByteBuffer byteBuffer = channel.map(FileChannel.MapMode.READ_ONLY, 0 , channel.size());
            System.out.println(str);

            file.close();
            channel.close();

            return Mono.just(DefaultPayload.create(byteBuffer));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Mono.just(DefaultPayload.create("Hello"));
    }
}
