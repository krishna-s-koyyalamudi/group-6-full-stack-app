package edu.nwmsu.group6.hunt6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Hunt6Application {

	public static void main(String[] args) {
		SpringApplication.run(Hunt6Application.class, args);
	}

}
