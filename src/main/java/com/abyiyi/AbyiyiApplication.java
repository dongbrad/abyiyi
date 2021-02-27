package com.abyiyi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations = {"classpath:config/abyiyi-lifecycle.xml"})
public class AbyiyiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AbyiyiApplication.class, args);
	}

}
