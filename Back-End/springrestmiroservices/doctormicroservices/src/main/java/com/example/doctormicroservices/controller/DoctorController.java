package com.example.doctormicroservices.controller;

import java.io.IOException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.doctormicroservices.entity.Doctor;
import com.example.doctormicroservices.exception.AppointmentNotFoundException;
import com.example.doctormicroservices.exception.AuthorizationException;
import com.example.doctormicroservices.exception.DoctorNotFoundException;
import com.example.doctormicroservices.model.Appointment;
import com.example.doctormicroservices.service.DoctorService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;



@RestController
@CrossOrigin
@Slf4j
public class DoctorController {

	
	@Autowired
	private DoctorService doctorService;

	@GetMapping(value = "/distinctspecialization")
	public List<String> getDistinctSpecialization() throws IOException {
		//log.info("getDistinctSpecialization() Started");
		List<String> spec =  doctorService.getDistinctSpecialization();
		//log.info("Specializations = {}",spec);
		//log.info("getDistinctSpecialization() End");
		return spec;
	}
	@GetMapping(value = "/getdoctorbyspecialist/{specialist}")
	public List<Doctor> getDoctorBySpecialist(@PathVariable("specialist") String specialist) throws DoctorNotFoundException {
		//log.info("getDoctorBySpecialist() Started");
		List<Doctor> doctor =  doctorService.getDoctorBySpecialist(specialist);
		//log.info("Doctor = {}",doctor);
		//log.info("getDoctorBySpecialist() End");
		return doctor;
	}

	@GetMapping(value = "/getdoctor/{id}")
	public Doctor getDoctorById(@PathVariable("id") String id, @RequestHeader("Authorization") String token) throws DoctorNotFoundException, AuthorizationException {
		//log.info("getDoctorById() Started");
		Doctor doctor =  doctorService.getDoctorById(id, token);
		//log.info("Doctor = {}",doctor);
		//log.info("getDoctorById() End");
		return doctor;
	}
	
	@PutMapping(value = "/updatedoctor/{Id}")
	public Doctor updateDoctor(@RequestHeader(value = "Authorization") String token,
			@PathVariable(value = "Id") String id, @RequestBody @Valid Doctor doctor)
			throws AuthorizationException, DoctorNotFoundException {
		//log.info("updateDoctor() Started");
		Doctor doctor1 = doctorService.updateDoctor(token,id,doctor);
		//log.info("Doctor = {}",doctor);
		//log.info("updateDoctor() End");
		return doctor1;
	}
	
	@PutMapping(value="/updatestatus/{id}/{status}/{remark}")
	public Appointment updateStatus(@RequestHeader(value = "Authorization") String token, @PathVariable("id") int id, @PathVariable("status") String status, @PathVariable("remark") String remark) throws AppointmentNotFoundException, AuthorizationException {
		return doctorService.updateStatus(id, status, remark,token);
	}
	
}