package com.foodsaver.foodsaver_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FoodsaverBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodsaverBackendApplication.class, args);
	}

}
