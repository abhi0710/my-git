package com.abhi0710.rsocket.plain;

import com.abhi0710.rsocket.plain.request.FileRequest;
import com.abhi0710.rsocket.plain.request.FileResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.rsocket.AbstractRSocket;
import io.rsocket.Payload;
import io.rsocket.ResponderRSocket;
import io.rsocket.util.DefaultPayload;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.time.Instant;
import java.util.concurrent.ConcurrentHashMap;

public class RSocketImpl extends AbstractRSocket implements ResponderRSocket {

    private static int maxC = 0;

    private static ObjectMapper objectMapper = new ObjectMapper();

    private static ConcurrentHashMap map = new ConcurrentHashMap();

    private static ConcurrentHashMap removed = new ConcurrentHashMap();

    @Override
    public Flux<Payload> requestStream(Payload payload) {


        String str = payload.getDataUtf8();
        System.out.println(payload.getMetadataUtf8());
        payload.release();


        FileRequest request = null;
        try {
            request = objectMapper.readValue(str, FileRequest.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        final String fileName = request.getFileName();
        int clientid = request.getClientId();

        Flux<Payload> flux = Flux.create(fluxSink -> {

            try (RandomAccessFile file = new
                    RandomAccessFile(fileName, "r")) {


                FileChannel channel = file.getChannel();

                ByteBuffer byteBuffer = null;
                int blockNumber = 0;

                map.put(clientid, clientid);


                do {
                    System.out.println(removed.keySet());
                    System.out.println(map.keySet());

                    maxC = Math.max(map.entrySet().size(), maxC);

                    System.out.println("size concurrency" + maxC);
                    FileRequest partRequest = new FileRequest(fileName, "1", blockNumber, clientid);

                    byteBuffer = getBlockBuffer(partRequest, channel);

                    fluxSink.next(DefaultPayload.create(byteBuffer,
                                ByteBuffer.wrap(objectMapper.writeValueAsBytes(new FileResponse(blockNumber)))));

                    blockNumber++;


                }while (byteBuffer!= null && byteBuffer.capacity() > 0);

                fluxSink.complete();
                System.out.println("removing client" + clientid);
                removed.put(clientid,  clientid);
                map.remove(clientid);


            } catch (Exception e) {
                System.out.println("Failed " );
                e.printStackTrace();
            }

        });



        return flux;
    }

    private ByteBuffer getBlockBuffer(FileRequest request, FileChannel channel) throws IOException {

        System.out.println("Client Id " + request.getClientId());
        long startBlock = request.getBlock() * 5 * 1024 * 1024;
        long size = 5 * 1024 * 1024;

        long endBlock = startBlock + size - 1;


        if (endBlock > channel.size())
            size = channel.size() - startBlock;

        System.out.println("Block " + request.getBlock() + " from " + startBlock
                + " to " + endBlock + " fileChannel " + channel.size());

        if (channel.size() < startBlock)
            return ByteBuffer.allocate(0);

        if (channel.size() < endBlock) {
            MappedByteBuffer byteBuffer = channel.map(
                    FileChannel.MapMode.READ_ONLY, startBlock,
                    size
            );
            return byteBuffer;
        } else {
            MappedByteBuffer byteBuffer = channel.map(
                    FileChannel.MapMode.READ_ONLY, startBlock, size
            );
            return byteBuffer;
        }
    }


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

                  return Mono.just(DefaultPayload.create(getBlockBuffer(request, channel)));
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
