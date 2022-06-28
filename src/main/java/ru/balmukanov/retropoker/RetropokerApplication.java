package ru.balmukanov.retropoker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ru.balmukanov.retropoker")
public class RetropokerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetropokerApplication.class, args);
	}

}
