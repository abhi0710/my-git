package com.abhi0710.rsocket.plain.client;

import com.abhi0710.rsocket.plain.request.FileRequest;
import com.abhi0710.rsocket.plain.request.FileResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.frame.decoder.PayloadDecoder;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.util.DefaultPayload;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.publisher.ParallelFlux;
import reactor.core.scheduler.Schedulers;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


//-Dreactor.netty.ioWorkerCount=200 -Dreactor.netty.ioSelectCount=32
public class RSocketClientBigFile {


//    static RSocket socket;


    public static void main(String[] args) throws InterruptedException {

        System.out.println(new Date());
        int TOTALDOWNLOADS = 500;
        ExecutorService service = Executors.newFixedThreadPool(TOTALDOWNLOADS);

        List<Callable<Integer>> callables = new ArrayList<>();

        for(int i = 0 ; i < TOTALDOWNLOADS ; i++) {
            final int index = i;
            callables.add(
                    new Callable<Integer>() {
                        @Override
                        public Integer call() throws Exception {
                            singleRequest(index);
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



        ExecutorService service = Executors.newFixedThreadPool(30);
        List<Callable<Integer>> list = new ArrayList<>();


        for (int i = 0; i < 11; i++) {

            final int block = i;
            list.add(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {

                    FileRequest request = new FileRequest("/usr/lib/jvm/openjdk-11/lib/src.zip", "1", block, index);
                    writeBlock(null, channel, request);

                    return 1;
                }
            });
        }
        channel.force(true);

        Instant start = Instant.now();

        service.invokeAll(list);

        Instant end = Instant.now();



        System.out.println(Duration.between(start, end).toSeconds());


        channel.force(true);
    }

    public static void singleRequest(int index) throws Exception {

        File file = new File("/home/gaian/uploads/split6/temp/" + System.currentTimeMillis() + "_" + index + ".zip");
        file.createNewFile();

        FileChannel channel = new RandomAccessFile(file, "rw")
                .getChannel();





        FileRequest request = new FileRequest("/usr/lib/jvm/openjdk-11/lib/src.zip", "1", 1, index);
        writeFile(null, channel, request);


        channel.force(true);

        Instant start = Instant.now();


        Instant end = Instant.now();



        System.out.println(Duration.between(start, end).toSeconds());


        channel.force(true);
    }

    private static void writeFile(Object o, FileChannel channel, FileRequest request) throws Exception {
        TcpClientTransport clientTransport = TcpClientTransport.create("localhost", 5000);


        RSocket socket = RSocketFactory.connect()
                .frameDecoder(PayloadDecoder.ZERO_COPY)
                .keepAliveAckTimeout(Duration.ofMinutes(10))
                .keepAliveTickPeriod(Duration.ofMinutes(100))
                .transport(clientTransport)
                .start()
                .block();

        Payload requestPayload = DefaultPayload.create(new ObjectMapper().writeValueAsString(request), "getFileList");

//        socket
//                .requestStream(requestPayload)
//                .map(Payload::getDataUtf8)
//                .doOnNext(System.out::println)
//                .then()
//                .doFinally(signalType -> socket.dispose())


        Flux<Payload> flux = socket.requestStream(requestPayload);
        flux.parallel(4).subscribe(new Subscriber<Payload>() {

            private int onNext = 0;
            private Subscription subscription;
            @Override
            public void onSubscribe(Subscription subscription) {
                subscription.request(2);
                this.subscription = subscription;
            }

            @Override
            public void onNext(Payload payload) {

                try {
                    System.out.println("recieved");
                    final FileResponse response =
                            new ObjectMapper().readValue(payload.getMetadataUtf8(), FileResponse.class);


                    System.out.println(response.getBlock());

                    channel.write(payload.getData(), response.getBlock() * (5 * 1024 * 1024));

                    System.out.println(Thread.currentThread().getName());

                    channel.force(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                onNext ++;

                if (onNext%2 ==0)
                    subscription.request(2);

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        });

//        flux.subscribe(payload -> System.out.println(payload));

//        socket.dispose();

        Thread.sleep(100000);
    }


    public static void writeBlock(RSocket socket, FileChannel channel, FileRequest request) throws Exception {
        try {

            System.out.println("in ");

             TcpClientTransport clientTransport = TcpClientTransport.create("localhost", 5000);


             socket = RSocketFactory.connect()
                    .frameDecoder(PayloadDecoder.ZERO_COPY)
                     .keepAliveAckTimeout(Duration.ofMinutes(10))
                     .keepAliveTickPeriod(Duration.ofMinutes(100))
                    .transport(clientTransport)
                    .start()
                    .block();

            Payload requestPayload = DefaultPayload.create(new ObjectMapper().writeValueAsString(request), "getFileList");

            Payload payload = socket.requestResponse(requestPayload).block();
            channel.write(payload.getData(), request.getBlock() * (5 * 1024 * 1024));

//            channel.force(true);

//            socket.dispose();

        }catch (Throwable e) {
            e.printStackTrace();
            System.out.println(new Date());
            System.out.println("Client error at block " + request.getBlock());
//            System.exit(1);
        }
    }
}
