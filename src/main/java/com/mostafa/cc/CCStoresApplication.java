package com.mostafa.cc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CCStoresApplication {

	public static void main(String[] args) {
		SpringApplication.run(CCStoresApplication.class, args);
	}
}
