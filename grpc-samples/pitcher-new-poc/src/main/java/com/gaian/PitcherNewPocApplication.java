package com.gaian;

import com.gaian.config.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({
		FileStorageProperties.class
})
@SpringBootApplication
public class PitcherNewPocApplication {

	public static void main(String[] args) {
		SpringApplication.run(PitcherNewPocApplication.class, args);
	}

}
