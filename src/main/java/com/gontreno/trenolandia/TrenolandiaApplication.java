package com.gontreno.trenolandia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages={
		"com.gontreno.trenolandia.arrival_inspector"})

public class TrenolandiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrenolandiaApplication.class, args);
	}

}
