package com.example.appointmentmicroservices.controller;

import java.util.List;

import org.slf4j.LoggerFactory;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.appointmentmicroservices.entity.Appointment;
import com.example.appointmentmicroservices.exception.AppointmentNotFoundException;
import com.example.appointmentmicroservices.model.Patient;
import com.example.appointmentmicroservices.service.AppointmentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin
@Slf4j
public class AppointmentController {
	
	Logger log = LoggerFactory.getLogger(AppointmentController.class);


	@Autowired
	private AppointmentService appointmentService;

	 

	@PutMapping(value="/updatestatus/{id}/{status}/{remark}")
	public Appointment updateStatus(@PathVariable("id") int id, @PathVariable("status") String status, @PathVariable("remark") String remark) throws AppointmentNotFoundException {
		log.info("updateStatus() Started");
		Appointment appointment =  appointmentService.updateStatus(id, status, remark);
		log.info("Appointment = {}",appointment);
		log.info("updateStatus() End");
		return appointment;
	}
	
	@GetMapping(value="/getallappointment/{doctorName}")
	public List<Appointment> getAllAppointment(@PathVariable("doctorName") String doctorName) {
		log.info("getAllAppointment() Started");
		List<Appointment> appointments = appointmentService.getAllAppointment(doctorName);
		log.info("Appointments = {}",appointments);
		log.info("getAllAppointment() End");
		return appointments;
	}
	
	@PostMapping(value = "/makeappointment")
	public Appointment makeAppointment(@RequestBody Patient patient)  {
		log.info("makeAppointment() Started");
		Appointment appointment = appointmentService.makeAppointment(patient);
		log.info("Appointment = {}",appointment);
		log.info("makeAppointment() End");
		return appointment;
	}
	
	@GetMapping(value="/viewappointment/{id}")
	public Appointment viewAppointment(@PathVariable("id") int id) throws AppointmentNotFoundException {
		log.info("viewAppointment() Started");
		Appointment appointment = appointmentService.viewAppointment(id);
		log.info("Appointment = {}",appointment);
		log.info("viewAppointment() End");
		return appointment;
	}
	
}
