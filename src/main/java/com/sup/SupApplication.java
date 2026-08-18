package com.sup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SupApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupApplication.class, args);
	}
}
