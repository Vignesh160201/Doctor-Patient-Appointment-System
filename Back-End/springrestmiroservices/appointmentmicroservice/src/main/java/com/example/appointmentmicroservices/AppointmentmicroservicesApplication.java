package com.example.appointmentmicroservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients

public class AppointmentmicroservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppointmentmicroservicesApplication.class, args);
	}

}
