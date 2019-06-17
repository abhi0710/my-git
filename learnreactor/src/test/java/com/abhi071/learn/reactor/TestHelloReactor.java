package com.abhi071.learn.reactor;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

public class TestHelloReactor {

    @Test
    public void testHello() throws InterruptedException {

        Integer array[] = new Integer[] {Integer.valueOf(1), Integer.valueOf(2),Integer.valueOf(3),Integer.valueOf(1), Integer.valueOf(2),Integer.valueOf(3),Integer.valueOf(1), Integer.valueOf(2),Integer.valueOf(3)};
        Flux.fromArray(array)
//                delayElements(Duration.ofSeconds(2))
//                .doOnNext(System.out::println)
//                .map(integer -> integer * integer)
                .parallel(8)
                .runOn(Schedulers.newElastic("test"))
                .subscribe(integer -> {
                    System.out.println(Thread.currentThread().getName());
                    System.out.println(integer);
                });

        Thread.sleep(150000);
    }
}
