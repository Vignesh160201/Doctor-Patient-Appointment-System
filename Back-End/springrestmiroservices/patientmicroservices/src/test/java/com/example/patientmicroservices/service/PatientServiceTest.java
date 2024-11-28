package com.example.patientmicroservices.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.patientmicroservices.client.AppointmentClient;
import com.example.patientmicroservices.client.DoctorClient;
import com.example.patientmicroservices.exception.AppointmentNotFoundException;
import com.example.patientmicroservices.exception.DoctorNotFoundException;
import com.example.patientmicroservices.model.Appointment;
import com.example.patientmicroservices.model.Doctor;
import com.example.patientmicroservices.model.Patient;

@SuppressWarnings("unused")
public class PatientServiceTest {

	LocalDate date = LocalDate.now();

	@Mock
	DoctorClient doctorClient;
	@Mock
	AppointmentClient appointmentClient;
	@InjectMocks
	PatientService patientService;

	@SuppressWarnings("deprecation")
	@BeforeEach
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void makeAppointmentTest() throws DoctorNotFoundException {
		

		Patient patient = new Patient("Rithika", 22, "FeMale", "1234657891", "Ram", "Dentist", date, "4 to 6");
		Appointment appointment = new Appointment();
		appointment.setDateOfAppointment(patient.getDateOfAppointment());
		appointment.setDoctorName(patient.getDoctorId());
		appointment.setPatientName(patient.getPatientName());
		appointment.setStatus("Pending");
		appointment.setTimeSlot(patient.getTimeSlot());
		List<Doctor> doctor = new ArrayList<>();
		doctor.add(new Doctor("vignesh", "MNND", "General", "New", "9874561"));
		doctor.add(new Doctor("hemanth", "MNND", "Dentist", "New", "9874561"));
		DoctorNotFoundException thrown = 
				org.junit.jupiter.api.Assertions.assertThrows(DoctorNotFoundException.class,
				() -> {	
		when(doctorClient.getDoctorBySpecialist("Dentist")).thenReturn(doctor);
		when(patientService.makeAppointment(patient)).thenReturn(null);
				});
	}

	@Test
	public void makeAppointmentExceptionTest() throws DoctorNotFoundException {

		Patient patient = new Patient("Rithika", 22, "FeMale", "1234657891", "hemant", "Dentist", date, "4 to 6");
		Appointment appointment = new Appointment();
		appointment.setDateOfAppointment(patient.getDateOfAppointment());
		appointment.setDoctorName(patient.getDoctorId());
		appointment.setPatientName(patient.getPatientName());
		appointment.setStatus("Pending");
		appointment.setTimeSlot(patient.getTimeSlot());
		List<Doctor> doctor = new ArrayList<>();
		doctor.add(new Doctor("vignesh", "MNND", "General", "New", "9874561"));
		doctor.add(new Doctor("hemanth", "MNND", "Dentist", "New", "9874561"));
		DoctorNotFoundException thrown = org.junit.jupiter.api.Assertions.assertThrows(DoctorNotFoundException.class,
				() -> {
					when(doctorClient.getDoctorBySpecialist("General")).thenReturn(doctor);
					when(patientService.makeAppointment(patient)).thenReturn(appointment);
				});
	}

	@Test
	public void viewAppointmentTest() throws AppointmentNotFoundException {
		Appointment appointment = new Appointment(1, "Tushar", "Hemant", date, "4 to 6", "Pending", null, 12, "Male",
				987453252);
		when(appointmentClient.viewAppointment(1)).thenReturn(appointment);
		when(patientService.viewAppointment(1)).thenReturn(appointment);
		assertThat(patientService.viewAppointment(1)).isEqualTo(appointment);
	}

	@Test
	public void viewAppointmentTestException() throws AppointmentNotFoundException {
		Appointment appointment = new Appointment(2, "Tushar", "Hemant", date, "4 to 6", "Pending", null, 12, "Male",
				987453252);
		AppointmentNotFoundException thrown = org.junit.jupiter.api.Assertions
				.assertThrows(AppointmentNotFoundException.class, () -> {
					when(appointmentClient.viewAppointment(2)).thenReturn(null);
					when(patientService.viewAppointment(2)).thenReturn(null);
				});
	}
}