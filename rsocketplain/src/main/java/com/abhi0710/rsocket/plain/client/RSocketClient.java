package com.abhi0710.rsocket.plain.client;

import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.frame.decoder.PayloadDecoder;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.util.DefaultPayload;
import reactor.core.publisher.Flux;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RSocketClient {

    static RSocket socket;

    public static void main(String[] args) {
        socket = RSocketFactory.connect()
                .frameDecoder(PayloadDecoder.ZERO_COPY)
                .transport(TcpClientTransport.create("localhost", 5000))
                .start()
                .block();


        ExecutorService service = Executors.newFixedThreadPool(300  );
        List<Callable<Integer>> list = new ArrayList<>();

        Instant start = Instant.now();

        for (int i = 0 ; i < 700 ; i++ ) {

            final int integer = i;

            list.add(new Callable<Integer>() {
                public Integer call() {

                    try {
                        File file = new File("/home/gaian/uploads/split6/temp/" + integer + ".pdf");
                        file.createNewFile();

                        FileChannel channel = new RandomAccessFile(file, "rw")
                                .getChannel();

                        Payload payload = socket.requestResponse(DefaultPayload.create("Hello")).block();

                        channel.write(payload.getData());
                        payload.release();
                        channel.close();

                        return 1;


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return 0;

                }
            });

        }

        try {
            service.invokeAll(list);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Instant finish = Instant.now();

        System.out.println(Duration.between(start, finish).toSeconds());
    }
}
