package com.abhi0710.rsocket.plain;


import io.rsocket.AbstractRSocket;
import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.transport.netty.server.TcpServerTransport;
import io.rsocket.util.DefaultPayload;
import reactor.core.publisher.Mono;

public final class Sample {

    public static void main(String[] args) {
        RSocketFactory.receive()
                .acceptor(
                        (setupPayload, reactiveSocket) ->
                                Mono.just(
                                        new AbstractRSocket() {
                                            boolean fail = true;

                                            @Override
                                            public Mono<Payload> requestResponse(Payload p) {
                                                if (fail) {
                                                    fail = false;
                                                    return Mono.error(new Throwable());
                                                } else {
                                                    return Mono.just(p);
                                                }
                                            }
                                        }))
                .transport(TcpServerTransport.create("localhost", 7000))
                .start()
                .subscribe();

        RSocket socket =
                RSocketFactory.connect()
                        .transport(TcpClientTransport.create("localhost", 7000))
                        .start()
                        .block();

        socket
                .requestResponse(DefaultPayload.create("Hello"))
                .map(Payload::getDataUtf8)
                .onErrorReturn("error")
                .doOnNext(System.out::println)
                .block();

        socket
                .requestResponse(DefaultPayload.create("Hello"))
                .map(Payload::getDataUtf8)
                .onErrorReturn("error")
                .doOnNext(System.out::println)
                .block();

        socket
                .requestResponse(DefaultPayload.create("Hello"))
                .map(Payload::getDataUtf8)
                .onErrorReturn("error")
                .doOnNext(System.out::println)
                .block();

        socket.dispose();
    }

}
