package com.example.doctormicroservices.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.doctormicroservices.exception.AppointmentNotFoundException;
import com.example.doctormicroservices.model.Appointment;


@FeignClient(name = "${appointment.name}", url = "${appointment.url}")
public interface AppointmentClient {

	@PutMapping(value="/updatestatus/{id}/{status}/{remark}")
	public Appointment updateStatus(@PathVariable("id") int id, @PathVariable("status") String status, @PathVariable("remark") String remark) throws AppointmentNotFoundException;
}
