package com.abhi0710.rsocket.plain.client;

import com.abhi0710.rsocket.plain.request.FileRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.frame.decoder.PayloadDecoder;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.util.DefaultPayload;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


//-Dreactor.netty.ioWorkerCount=200 -Dreactor.netty.ioSelectCount=32
public class RSocketClientBigFile {


//    static RSocket socket;


    public static void main(String[] args) throws InterruptedException {

        int TOTALDOWNLOADS = 3;
        ExecutorService service = Executors.newFixedThreadPool(TOTALDOWNLOADS);

        List<Callable<Integer>> callables = new ArrayList<>();

        for(int i = 0 ; i < TOTALDOWNLOADS ; i++) {
            final int index = i;
            callables.add(
                    new Callable<Integer>() {
                        @Override
                        public Integer call() throws Exception {
                            downloadFullFile(index);
                            return 1;
                        }
                    }
            );
        }


        Instant start = Instant.now();
        service.invokeAll(callables);
        Instant end = Instant.now();

        System.out.println(Duration.between(start, end).toSeconds());

        System.exit(1);

    }

    public static void downloadFullFile(int index) throws Exception {

        File file = new File("/home/gaian/uploads/split6/temp/" + System.currentTimeMillis() + "_" + index + ".zip");
        file.createNewFile();

        FileChannel channel = new RandomAccessFile(file, "rw")
                .getChannel();



        ExecutorService service = Executors.newFixedThreadPool(4);
        List<Callable<Integer>> list = new ArrayList<>();


        for (int i = 0; i < 60; i++) {

            final int block = i;
            list.add(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {

                    TcpClientTransport clientTransport = TcpClientTransport.create("localhost", 80);


                    final RSocket socket = RSocketFactory.connect()
                            .frameDecoder(PayloadDecoder.ZERO_COPY)
                            .transport(clientTransport)
                            .start()
                            .block();
                    FileRequest request = new FileRequest("/usr/lib/jvm/openjdk-11/lib/src.zip", "1", block);
                    writeBlock(socket, channel, request);
                    channel.force(true);
                    socket.dispose();
                    return 1;
                }
            });
        }

        Instant start = Instant.now();

        service.invokeAll(list);

        Instant end = Instant.now();


        System.out.println(Duration.between(start, end).toSeconds());


        channel.force(true);
    }

    public static void writeBlock(RSocket socket, FileChannel channel, FileRequest request) throws Exception {
        try {



            Payload requestPayload = DefaultPayload.create(new ObjectMapper().writeValueAsString(request), "getFileList");

            Payload payload = socket.requestResponse(requestPayload).block();
            channel.write(payload.getData(), request.getBlock() * (1024 * 1024));


        }catch (Throwable e) {
            System.out.println("Client error at block " + request.getBlock());
        }
    }
}
