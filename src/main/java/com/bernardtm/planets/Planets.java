package com.bernardtm.planets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

@SpringBootApplication
public class Planets {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(Planets.class);
		springApplication.addListeners(new ApplicationPidFileWriter("planets.pid"));
		springApplication.run(args);
	}

}
