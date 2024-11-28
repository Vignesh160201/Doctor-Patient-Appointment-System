package com.example.doctormicroservices;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.doctormicroservices.service.DoctorService;

@SpringBootApplication
@EnableFeignClients
public class DoctormicroservicesApplication {

	public static void main(String[] args) throws IOException {
		ConfigurableApplicationContext cts = SpringApplication.run(DoctormicroservicesApplication.class, args);
		 DoctorService doctorService = cts.getBean(DoctorService.class);
		doctorService.readDoctorsFromCSV();
		
	}

}
