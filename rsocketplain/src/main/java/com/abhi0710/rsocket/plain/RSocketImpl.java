package com.abhi0710.rsocket.plain;

import io.rsocket.AbstractRSocket;
import io.rsocket.Payload;
import io.rsocket.util.DefaultPayload;
import reactor.core.publisher.Mono;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class RSocketImpl extends AbstractRSocket {

    @Override
    public Mono<Payload> requestResponse(Payload payload) {
        payload.release();

        try {
            RandomAccessFile file = new
                    RandomAccessFile("/home/gaian/uploads/split6/Avatar Brochure Mail_01082018.pdf", "r");

            FileChannel channel = file.getChannel();
            MappedByteBuffer byteBuffer = channel.map(FileChannel.MapMode.READ_ONLY, 0 , channel.size());

            file.close();
            channel.close();


            return Mono.just(DefaultPayload.create(byteBuffer));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Mono.just(payload);
    }
}
