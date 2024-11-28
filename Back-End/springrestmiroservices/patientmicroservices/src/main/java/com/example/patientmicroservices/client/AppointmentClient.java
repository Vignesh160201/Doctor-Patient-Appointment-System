package com.example.patientmicroservices.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.patientmicroservices.model.Appointment;
import com.example.patientmicroservices.model.Patient;

@FeignClient(name = "${appointment.name}", url = "${appointment.url}")
public interface AppointmentClient {

	@PostMapping(value="makeappointment")
	public Appointment makeAppointment(@RequestBody Patient patient);

	@GetMapping(value="/viewappointment/{id}")
	public Appointment viewAppointment(@PathVariable("id") int id);
	
}
