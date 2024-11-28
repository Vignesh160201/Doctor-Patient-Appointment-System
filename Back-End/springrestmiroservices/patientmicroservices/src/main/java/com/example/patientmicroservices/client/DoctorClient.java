package com.example.patientmicroservices.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.patientmicroservices.exception.DoctorNotFoundException;
import com.example.patientmicroservices.model.Doctor;


@FeignClient(name = "${doctor.name}", url = "${doctor.url}")
public interface DoctorClient {

	@GetMapping(value = "/getdoctorbyspecialist/{specialist}")
	public List<Doctor> getDoctorBySpecialist(@PathVariable("specialist") String specialist) throws DoctorNotFoundException;
}
