package com.example.springreactive;

import net.engio.mbassy.bus.MBassador;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

        MBassador bus = new MBassador();
        bus.subscribe(new Listener());

        bus.post("asasas").now();
        bus.post(4).asynchronously();

        LinkedList a = new LinkedList<>();
        a.add(1);
        bus.post(a).now();
        a.add(2);
        bus.post(a).asynchronously();
	}

}
