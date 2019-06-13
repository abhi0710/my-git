package com.abhi0710.rsocket.plain;

import io.rsocket.Closeable;
import io.rsocket.RSocketFactory;
import io.rsocket.transport.netty.server.TcpServerTransport;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

public class RSocketServer {

    private static Closeable server;

    public static void main(String[] args) {
        server = RSocketFactory.receive()
                .acceptor((setupPayload, reactiveSocket) -> Mono.just(new RSocketImpl()))
                .transport(TcpServerTransport.create("localhost", 5000))
                .start()
                .block();

        System.out.println(server);

        server.onClose().block();

    }
}
