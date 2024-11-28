package com.example.patientmicroservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PatientmicroservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientmicroservicesApplication.class, args);
	}

}
