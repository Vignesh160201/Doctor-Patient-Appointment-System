package com.cts.authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AuthorizationmicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorizationmicroserviceApplication.class, args);
	} 

}
