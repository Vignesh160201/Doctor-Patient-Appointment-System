package com.example.appointmentmicroservices.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.appointmentmicroservices.entity.Appointment;
import com.example.appointmentmicroservices.exception.AppointmentNotFoundException;
import com.example.appointmentmicroservices.model.Patient;
import com.example.appointmentmicroservices.repository.AppointmentRepository;

import jakarta.transaction.Transactional;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Transactional
	public Appointment updateStatus(int id, String status, String remark) throws AppointmentNotFoundException {
		Appointment appointment = appointmentRepository.findById(id).orElse(null);
		if (appointment != null) {
			appointment.setStatus(status);
			appointment.setRemark(remark);
			appointmentRepository.save(appointment);
			return appointment;
		}
		throw new AppointmentNotFoundException("appointment not found");
	}

	@Transactional
	public List<Appointment> getAllAppointment(String doctorName) {
		return appointmentRepository.getAllAppointments(doctorName);
	}
	
	@Transactional
	public Appointment makeAppointment(Patient patient) {
		Appointment appointment = new Appointment();
		appointment.setDateOfAppointment(patient.getDateOfAppointment());
		appointment.setDoctorName(patient.getDoctorId());
		appointment.setPatientName(patient.getPatientName());
		appointment.setAge(patient.getAge());
		appointment.setContactNo(patient.getContactNo());
		appointment.setGender(patient.getGender());
		appointment.setStatus("Pending");
		appointment.setRemark("wait for the confimation");
		appointment.setTimeSlot(patient.getTimeSlot());
		return appointmentRepository.save(appointment);
		
	}
	@Transactional
	public Appointment viewAppointment(int id) {
		return appointmentRepository.findById(id).orElse(null);
	}

}
