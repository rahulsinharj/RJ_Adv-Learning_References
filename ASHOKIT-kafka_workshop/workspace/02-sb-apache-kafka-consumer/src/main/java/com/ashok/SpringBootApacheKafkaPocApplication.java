package com.ashok;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootApacheKafkaPocApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootApacheKafkaPocApplication.class, args);

		System.out.println("SBoot Apache Kafka Consumer : Application running");
	}
}
