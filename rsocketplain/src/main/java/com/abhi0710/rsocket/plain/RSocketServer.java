package com.abhi0710.rsocket.plain;

import io.netty.channel.ChannelOption;
import io.rsocket.Closeable;
import io.rsocket.RSocketFactory;
import io.rsocket.frame.decoder.PayloadDecoder;
import io.rsocket.transport.netty.server.TcpServerTransport;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;
import reactor.netty.tcp.TcpServer;

public class RSocketServer {

    private static Closeable server;

    public static void main(String[] args) {
        server = RSocketFactory.receive()
                .frameDecoder(PayloadDecoder.ZERO_COPY)
                .acceptor((setupPayload, reactiveSocket) -> Mono.just(new RSocketImpl()))
                .transport(
                        TcpServerTransport.create(TcpServer.create().port(5000).host("localhost").
                                option(ChannelOption.AUTO_CLOSE, true)
                                .option(ChannelOption.SO_BACKLOG,500)
                        .option(ChannelOption.SO_TIMEOUT,10000))
                )
                .start()
                .block();

        System.out.println(server);

        server.onClose().block();

    }
}
