package com.abhi0710.rsocket.plain.client;

import com.abhi0710.rsocket.plain.request.FileRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.concurrent.*;


//-Dreactor.netty.ioWorkerCount=200 -Dreactor.netty.ioSelectCount=32
public class RSocketClient {



//    static RSocket socket;

    public static void main(String[] args) {
//        socket = RSocketFactory.connect()
//                .frameDecoder(PayloadDecoder.ZERO_COPY)
//                .transport(TcpClientTransport.create("localhost", 5000))
//                .start()
//                .block();



        ExecutorService service = Executors.newFixedThreadPool(8  );
        List<Callable<Integer>> list = new ArrayList<>();


        for (int i = 0 ; i < 500 ; i++ ) {

            final int integer = i;



            list.add(new Callable<Integer>() {
                public Integer call() {

                    try {

                        final RSocket socket = RSocketFactory.connect()
                                .keepAliveAckTimeout(Duration.ofSeconds(100))
                                .frameDecoder(PayloadDecoder.ZERO_COPY)
                                .transport(TcpClientTransport.create("localhost", 80))
                                .start()
                                .block();


                        File file = new File("/home/gaian/uploads/split6/temp/" + integer + ".pdf");
                        file.createNewFile();

                        FileChannel channel = new RandomAccessFile(file, "rw")
                                .getChannel();

                        FileRequest request = new FileRequest("a", "b", -1, integer);

                        Payload requestPayload = DefaultPayload.create(new ObjectMapper().writeValueAsString(request), "getFileList");

                        Payload payload = socket.requestResponse(requestPayload).block();

                        channel.write(payload.getData());
                        payload.release();
//                        channel.close();

//                        Thread.sleep(10000);


                        System.out.println("finished " + integer);

                                                socket.dispose();


                        return 1;


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return 0;

                }
            });

        }

        Instant start = Instant.now();


        try {
            service.invokeAll(list);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Instant finish = Instant.now();

        System.out.println(Duration.between(start, finish).toSeconds());
    }
}
