package com.group9.places;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan("com.group9.places")
public class PlacesApplication {

	private static Logger logger = LoggerFactory.getLogger(PlacesApplication.class);

	public static void main(String[] args) {

		logger.info("Starting the Microservice.");
		SpringApplication.run(PlacesApplication.class, args);
	}

}
