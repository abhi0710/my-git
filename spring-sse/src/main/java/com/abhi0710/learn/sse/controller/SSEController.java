package com.abhi0710.learn.sse.controller;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@RestController
public class SSEController {
    private Set<SseEmitter>  clients = new CopyOnWriteArraySet<>();

    @GetMapping(value = "/temp")
    public SseEmitter sseEmitter(){
        SseEmitter emitter = new SseEmitter();
        clients.add(emitter);

        emitter.onTimeout(() -> clients.remove(emitter));
        emitter.onTimeout(() -> clients.remove(emitter));

        return emitter;
    }

    @EventListener
    @Async
    public void handleMessage(Double temp){

        System.out.println(temp);
        List<SseEmitter> emitters = new ArrayList<>();

        System.out.println(clients.size());

        clients.forEach(sseEmitter -> {
            try {
                sseEmitter.send(Double.valueOf(temp));
            }catch (IOException e) {
                emitters.add(sseEmitter);
            }
        });

        clients.removeAll(emitters);
    }
}
