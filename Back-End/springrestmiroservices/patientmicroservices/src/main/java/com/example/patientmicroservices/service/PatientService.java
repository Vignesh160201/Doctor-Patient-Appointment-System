package com.example.patientmicroservices.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.patientmicroservices.client.AppointmentClient;
import com.example.patientmicroservices.client.DoctorClient;
import com.example.patientmicroservices.exception.AppointmentNotFoundException;
import com.example.patientmicroservices.exception.DoctorNotFoundException;
import com.example.patientmicroservices.model.Appointment;
import com.example.patientmicroservices.model.Doctor;
import com.example.patientmicroservices.model.Patient;



@Service
public class PatientService {

	@Autowired
	private AppointmentClient appointmentClient;
	
	@Autowired
	private DoctorClient doctorClient;
	
	
	public Appointment makeAppointment(Patient patient) throws DoctorNotFoundException {
		List<Doctor> doctor = doctorClient.getDoctorBySpecialist(patient.getSpecialization());

			for(Doctor d : doctor) {
				if(d.getDoctorName().equalsIgnoreCase(patient.getDoctorId()))
				{
					 return appointmentClient.makeAppointment(patient);
				}
			}
		 throw new DoctorNotFoundException("Doctor Not found for this specialist");
	}
	
	
	public Appointment viewAppointment(int id) throws AppointmentNotFoundException {
		 Appointment appointment = appointmentClient.viewAppointment(id);
		 if(appointment!=null) {
			 return appointment;
		 }
		 throw new AppointmentNotFoundException("apppointment not found");
	}

	
	


}
