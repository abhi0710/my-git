package com.example.springreactive;

import net.engio.mbassy.listener.Enveloped;
import net.engio.mbassy.listener.Handler;
import net.engio.mbassy.subscription.MessageEnvelope;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@net.engio.mbassy.listener.Listener
public class Listener {

    @Handler
    public void handle(String a) {
        System.out.println("Recieved string " + a + " in " + Thread.currentThread().getName());
    }

    @Handler
    public void handle(Integer a) {
        System.out.println("Recieved Integer" + a + " in " + Thread.currentThread().getName());
    }

    @Handler(condition = "msg.size() > 1")
    @Enveloped(messages = {HashMap.class,LinkedList.class})
    public void handle(MessageEnvelope envelope){
        System.out.println((Collection)envelope.getMessage());
    }
}
