package com.example.appointmentmicroservices.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.appointmentmicroservices.entity.Appointment;
import com.example.appointmentmicroservices.exception.AppointmentNotFoundException;
import com.example.appointmentmicroservices.model.Patient;
import com.example.appointmentmicroservices.repository.AppointmentRepository;

class AppointmentServiceTests {

	LocalDate date = LocalDate.now();
	@Mock
	AppointmentRepository appointmentRepository;
	
	@InjectMocks
	AppointmentService appointmentService;
	
	@SuppressWarnings("deprecation")
	@BeforeEach 
	public void initMocks() {
	    MockitoAnnotations.initMocks(this);
	    	}
	
	@Test
	public void makeAppointmentTest() {
		Patient patient = new Patient();
		Appointment appointment = new Appointment();
		appointment.setDateOfAppointment(patient.getDateOfAppointment());
		appointment.setDoctorName(patient.getDoctorId());
		appointment.setPatientName(patient.getPatientName());
		appointment.setStatus("Pending");
		appointment.setTimeSlot(patient.getTimeSlot());
		when(appointmentRepository.save(appointment)).thenReturn(appointment);
		assertNull(appointmentService.makeAppointment(patient));
		
	}
	
	@Test
	public void viewAppointmentTest() {
		Appointment appointment = new Appointment(1, "Tushar", "Hemant", date, "4 to 6", "Pending",null,12,"Male",987453252);
		when(appointmentRepository.findById(1)).thenReturn(Optional.of(appointment));
		assertThat(appointmentService.viewAppointment(1)).isEqualTo(appointment);
	}
	
	@Test
	public void viewAppointmentNullTest() throws AppointmentNotFoundException {
		when(appointmentRepository.findById(111)).thenReturn(null);
		assertThat(appointmentService.viewAppointment(1)).isEqualTo(null);

	}
	
	@Test
	public void updateStatusTest() throws AppointmentNotFoundException {
		String status = "verified";
		String remarks="two pills";
		Appointment appointment = new Appointment(1, "Tushar", "Hemant", date, "4 to 6", "Pending",null,12,"Male",987453252);
		when(appointmentRepository.findById(1)).thenReturn(Optional.of(appointment));
		Appointment appointment1 = appointmentRepository.findById(1).orElse(null);
		appointment1.setStatus(status);
		appointment1.setRemark(remarks);
		assertThat(appointmentService.updateStatus(1, status,remarks)).isEqualTo(appointment1);
		
	}

}
