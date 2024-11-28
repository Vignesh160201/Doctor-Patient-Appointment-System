package com.example.patientmicroservices.controller;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.patientmicroservices.exception.AppointmentNotFoundException;
import com.example.patientmicroservices.exception.DoctorNotFoundException;
import com.example.patientmicroservices.model.Appointment;
import com.example.patientmicroservices.model.Patient;
import com.example.patientmicroservices.service.PatientService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;


@RestController
@CrossOrigin
@Validated
@Slf4j
public class PatientController {

	private Logger log = LoggerFactory.getLogger(PatientController.class);

	
	@Autowired
	private PatientService patientService;
	
	@PostMapping(value="/makeappointment")
	public Appointment makeAppointment(@Valid @RequestBody Patient patient) throws DoctorNotFoundException {
		log.info("makeAppointment() Started");
		Appointment appointment = patientService.makeAppointment(patient);
		log.info("Appointment = {}",appointment);
		log.info("makeAppointment() End");
		return appointment;
	}
	
	@GetMapping(value="/viewappointment/{id}")
	public Appointment viewAppointment(@PathVariable("id") int id) throws AppointmentNotFoundException {
		log.info("viewAppointment() Started");
		Appointment appointment = patientService.viewAppointment(id);
		log.info("Appointment = {}",appointment);
		log.info("viewAppointment() End");
		return appointment;
	}
	
		

	
}
