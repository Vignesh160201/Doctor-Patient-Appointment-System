package com.example.doctormicroservices.service;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.doctormicroservices.client.AppointmentClient;
import com.example.doctormicroservices.client.AuthClient;
import com.example.doctormicroservices.entity.Doctor;
import com.example.doctormicroservices.exception.AppointmentNotFoundException;
import com.example.doctormicroservices.exception.AuthorizationException;
import com.example.doctormicroservices.exception.DoctorNotFoundException;
import com.example.doctormicroservices.repository.DoctorRepository;

class DoctorServiceTests {

	@Mock
	DoctorRepository doctorRepository;

	@InjectMocks
	DoctorService doctorService;

	@Mock
	AppointmentClient appointmentClient;
	@Mock
	AuthClient authClient;

	@SuppressWarnings("deprecation")
	@BeforeEach
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testgetDistinctSpecialization() {
		List<Doctor> list = new ArrayList<>();
		when(doctorRepository.findDistinctDoctorSpecialist()).thenReturn(null);
		assertThat(doctorService.getDistinctSpecialization()).isEqualTo(null);
	}
	@Test
	public void testgetDoctorById() throws DoctorNotFoundException, AuthorizationException {
		Doctor doctor = new Doctor();
		when(doctorRepository.findById(anyString())).thenReturn(Optional.of(doctor));
		when(authClient.authorizeTheRequest(anyString())).thenReturn(true);
		assertThat(doctorService.getDoctorById(anyString(), "Vignesh")).isEqualTo(doctor);
	}
	@Test
	public void testgetDoctorBySpecialist() throws DoctorNotFoundException {
		List<Doctor> list = new ArrayList<>();
		Doctor doctor = new Doctor();
		when(doctorRepository.findByDoctorSpecialist(doctor.getDoctorSpecialist())).thenReturn(list);
		assertThat(doctorService.getDoctorBySpecialist(doctor.getDoctorSpecialist())).isEqualTo(list);
	}

	@Test
	public void testupdateDoctor() throws DoctorNotFoundException, AuthorizationException {
		Doctor doctor = new Doctor("vignesh", "MNND", "General", "New", "9874561");
		when(authClient.authorizeTheRequest("@Authorization")).thenReturn(true);
		when(doctorRepository.findById("Vignesh")).thenReturn(Optional.of(doctor));
		assertThat(doctorService.updateDoctor("@Authorization", "Vignesh", doctor));
		
	}


	@Test
	public void testgetDoctorByIdException() throws DoctorNotFoundException, AuthorizationException {
		DoctorNotFoundException thrown = org.junit.jupiter.api.Assertions.assertThrows(DoctorNotFoundException.class,
				() -> {
					when(doctorRepository.findById("Vignesh")).thenReturn(null);
					when(authClient.authorizeTheRequest(anyString())).thenReturn(true);
					assertThat(doctorService.getDoctorById("Ravi", anyString())).isEqualTo(null);
				});
	}


	@Test
	public void testgetDoctorBySpecialistException() throws DoctorNotFoundException {
		DoctorNotFoundException thrown = org.junit.jupiter.api.Assertions.assertThrows(DoctorNotFoundException.class,
				() -> {
					when(doctorRepository.findByDoctorSpecialist(anyString())).thenReturn(null);
					assertThat(doctorService.getDoctorBySpecialist(anyString())).isEqualTo(null);
				});

	}


	@Test
	public void testupdateDoctorException() throws DoctorNotFoundException, AuthorizationException {
		DoctorNotFoundException thrown = org.junit.jupiter.api.Assertions.assertThrows(DoctorNotFoundException.class,
				() -> {
					Doctor doctor = new Doctor("vignesh", "MNND", "General", "New", "9874561");
					when(doctorRepository.findById("Vignesh")).thenReturn(null);
					when(authClient.authorizeTheRequest(anyString())).thenReturn(true);
					assertThat(doctorService.updateDoctor(anyString(), "Ram", doctor));
				});
	}

	@Test
	public void testgetDoctorByIdToken() throws DoctorNotFoundException, AuthorizationException {
		AuthorizationException thrown = org.junit.jupiter.api.Assertions.assertThrows(AuthorizationException.class,
				() -> {
					
					Doctor doctor = new Doctor();
					when(doctorRepository.findById("vignesh")).thenReturn(Optional.of(doctor));
					assertThat(doctorService.getDoctorById("vignesh", null)).isEqualTo(doctor);
				});
		
	}
	@Test
	public void testupdateDoctorToken() throws DoctorNotFoundException, AuthorizationException {
		AuthorizationException thrown = org.junit.jupiter.api.Assertions.assertThrows(AuthorizationException.class,
				() -> {

					Doctor doctor = new Doctor("vignesh", "MNND", "General", "New", "9874561");
					when(authClient.authorizeTheRequest("@Authorization")).thenReturn(true);
					when(doctorRepository.findById("Vignesh")).thenReturn(null);
					when(doctorService.updateDoctor("Authorization", "Vignesh", doctor))
					.thenReturn(null);
					
				});
	}
	
	@Test
	public void testupdateStatusException() throws AppointmentNotFoundException, AuthorizationException {
		AuthorizationException thrown = org.junit.jupiter.api.Assertions.assertThrows(AuthorizationException.class,
				() -> {
					when(doctorService.updateStatus(1, "Done", "two pills","Authorization")).thenReturn(null);				
				});
		
	}
	/*
	@Test
	public void testupdateStatus() throws DoctorNotFoundException, AuthorizationException, AppointmentNotFoundException {
		LocalDate date= LocalDate.now();
		AuthorizationException thrown = org.junit.jupiter.api.Assertions.assertThrows(AuthorizationException.class,
				() -> {
		Appointment appointment = new Appointment(1, "Tushar", "Hemant", date, "4 to 6", "Pending",null,12,"Male",987453252);
		when(authClient.authorizeTheRequest("@Authorization")).thenReturn(true);
		when(appointmentClient.updateStatus(1, "Done", "TwoPills")).thenReturn(appointment);
		when(doctorService.updateStatus(1, "Done", "TwoPills","@Authorization")).thenReturn(appointment);
		assertThat(doctorService.updateStatus(1, "Done", "Two Pills", "@Authorization"));
				});
	}*/
	
}