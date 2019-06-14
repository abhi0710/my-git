package com.abhi0710.rsocket.plain;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.rsocket.DuplexConnection;
import io.rsocket.RSocketFactory;
import io.rsocket.frame.decoder.PayloadDecoder;
import io.rsocket.transport.ServerTransport;
import io.rsocket.transport.netty.WebsocketDuplexConnection;
import reactor.netty.Connection;
import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServer;

import java.time.Duration;

public class WebSocketServer {


    public static void main(String[] args) {
        ServerTransport.ConnectionAcceptor acceptor =
                RSocketFactory.receive()
                        .frameDecoder(PayloadDecoder.ZERO_COPY)
                        .acceptor(new SocketAcceptorImpl())
                        .toConnectionAcceptor();



                HttpServer.create()
                        .host("localhost")
                        .port(5000)
                        .route(
                                routes ->
                                        routes.ws(
                                                "/",
                                                (in, out) -> {
                                                    if (in.headers().containsValue("Authorization", "test", true)) {
                                                        DuplexConnection connection =
                                                                new WebsocketDuplexConnection((Connection) in);
                                                        return acceptor.apply(connection).then(out.neverComplete());
                                                    }

                                                    return out.sendClose(
                                                            HttpResponseStatus.UNAUTHORIZED.code(),
                                                            HttpResponseStatus.UNAUTHORIZED.reasonPhrase());
                                                }))
                        .bindUntilJavaShutdown(Duration.ofMinutes(10), null);

    }
}
