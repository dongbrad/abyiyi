package com.abyiyi;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@ImportResource(locations = {"classpath:config/abyiyi-lifecycle.xml"})
public class AbyiyiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AbyiyiApplication.class, args);
	}

}
