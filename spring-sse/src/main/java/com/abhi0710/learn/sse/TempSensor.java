package com.abhi0710.learn.sse;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Component
public class TempSensor {

    private ApplicationEventPublisher publisher;

    public TempSensor(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @PostConstruct
    public void postConstruct() {
        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(this::probe, 1, 1, TimeUnit.SECONDS);
    }

    public void probe() {
        System.out.println("p");
        try {
            publisher.publishEvent(Double.valueOf(ThreadLocalRandom.current().nextGaussian() * 10));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
