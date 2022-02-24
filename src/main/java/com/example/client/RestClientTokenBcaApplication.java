package com.example.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class RestClientTokenBcaApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestClientTokenBcaApplication.class, args);
		
		System.out.println("Rest Client Token BCA Dijalankan");
	}
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
