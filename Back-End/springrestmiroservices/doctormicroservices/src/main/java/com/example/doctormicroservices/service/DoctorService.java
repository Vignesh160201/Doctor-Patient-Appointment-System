package com.example.doctormicroservices.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.doctormicroservices.client.AppointmentClient;
import com.example.doctormicroservices.client.AuthClient;
import com.example.doctormicroservices.entity.Doctor;
import com.example.doctormicroservices.exception.AppointmentNotFoundException;
import com.example.doctormicroservices.exception.AuthorizationException;
import com.example.doctormicroservices.exception.DoctorNotFoundException;
import com.example.doctormicroservices.model.Appointment;
import com.example.doctormicroservices.repository.DoctorRepository;

import jakarta.transaction.Transactional;

@Service
public class DoctorService {

	@Autowired
	private DoctorRepository doctorRepository;


	@Autowired
	private AuthClient authClient;
	
	@Autowired
	private AppointmentClient appointmentClient;
	
	@Transactional
	public List<String> getDistinctSpecialization() {
		return doctorRepository.findDistinctDoctorSpecialist();
	}

	@Transactional
	public void readDoctorsFromCSV() throws IOException {

		String splitBy = ",";
		String line;
		String path="./lib/Demo.csv";
		// parsing a CSV file into BufferedReader class constructor
		
		BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
		while ((line = bufferedReader.readLine()) != null) {

			String[] attributes = line.split(splitBy);
			Doctor doc = new Doctor(attributes[0], attributes[1], attributes[2], attributes[3], attributes[4]);
			doctorRepository.save(doc);

		}
		bufferedReader.close();

	}
	@Transactional
	public Doctor getDoctorById(String id, String token) throws DoctorNotFoundException, AuthorizationException {
		if (authClient.authorizeTheRequest(token)) {
			Doctor doctor = doctorRepository.findById(id).orElse(null);
			if (doctor != null) {
				return doctor;
			}
			else {throw new DoctorNotFoundException("Doctor  not found");}
		}

		throw new AuthorizationException("Token is not valid");

	}
	@Transactional
	public List<Doctor> getDoctorBySpecialist(String specialist) throws DoctorNotFoundException {
		List<Doctor> doctorSpecialist = doctorRepository.findByDoctorSpecialist(specialist);
		if (doctorSpecialist != null) {
			return doctorSpecialist;
		} else {
			throw new DoctorNotFoundException("Doctor Specialist not found");
		}
	}
	
	@Transactional
	public  Doctor updateDoctor(String token, String id, Doctor doctor)
			throws DoctorNotFoundException, AuthorizationException {
		if (authClient.authorizeTheRequest(token)) {
			Doctor updatedoctor = doctorRepository.findById(id).orElse(null);
			if (updatedoctor == null) {
				throw new DoctorNotFoundException("Doctor is Not Present for update");
			} else {
				if (doctor.getDoctorName().equalsIgnoreCase(id)) {
					
					return doctorRepository.save(doctor);
				} 
			}
		} 
			throw new AuthorizationException("Token is in valid");
	}
	  @Transactional
		public Appointment updateStatus(int id, String status, String remark, String token) throws AppointmentNotFoundException, AuthorizationException {
			if (authClient.authorizeTheRequest(token)) {
				return appointmentClient.updateStatus(id, status, remark);
			}
			throw new AuthorizationException("Token is invalid");
		}
}