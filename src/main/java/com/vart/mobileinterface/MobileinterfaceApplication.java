package com.vart.mobileinterface;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MobileinterfaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileinterfaceApplication.class, args);
	}

}
