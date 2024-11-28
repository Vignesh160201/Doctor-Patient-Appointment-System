package com.example.patientmicroservices.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.patientmicroservices.client.AppointmentClient;
import com.example.patientmicroservices.client.DoctorClient;
import com.example.patientmicroservices.model.Appointment;
import com.example.patientmicroservices.model.Doctor;
import com.example.patientmicroservices.model.Patient;
import com.example.patientmicroservices.service.PatientService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class PatientControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	AppointmentClient appointmentClient;

	@MockBean
	DoctorClient doctorClient;

	@MockBean
	PatientService patientService;

	private WebApplicationContext context;

	ObjectMapper objectMapper = new ObjectMapper();
	LocalDate date= LocalDate.now();


	@Before(value = "")
	private void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	@Test
	public void testAppointmentClient() {
		assertNotNull(appointmentClient);
	}

	@Test
	public void testDoctorClient() {
		assertNotNull(doctorClient);
	}

	@Test
	public void testPatientService() {
		assertNotNull(patientService);
	}

	@Test
	public void makeAppointmentTest() throws Exception {

		Patient patient = new Patient();
		patient.setPatientName("Rithika");
		patient.setAge(12);
		patient.setContactNo("9444025773");
		patient.setGender("Female");
		patient.setDoctorId("Vignesh");
		patient.setSpecialization("General");
		patient.setTimeSlot("02pm to 03pm");
		String jsonRequest = objectMapper.writeValueAsString(patient);
		Appointment appointment = new Appointment();
		List<Doctor> doctor = new ArrayList<>();
		when(doctorClient.getDoctorBySpecialist(patient.getSpecialization())).thenReturn(doctor);
		when(appointmentClient.makeAppointment(patient)).thenReturn(appointment);
		when(patientService.makeAppointment(patient)).thenReturn(appointment);
		this.mockMvc
				.perform(post("/makeappointment").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk());

	}

	@Test
	public void makeAppointmentFailTest() throws Exception {

		Patient patient = new Patient();
		patient.setPatientName("Rithika");
		patient.setAge(12);
		patient.setContactNo("1234657891");
		patient.setGender("Female");
		patient.setDoctorId("gnesh");
		patient.setSpecialization("General");
		patient.setTimeSlot("02pm to 03pm");
		String jsonRequest = objectMapper.writeValueAsString(patient);
		this.mockMvc.perform(post("/makeappointment").content(jsonRequest).contentType(MediaType.TEXT_PLAIN_VALUE))
				.andExpect(status().isUnsupportedMediaType());

	}

	@Test
	public void viewAppointmentTest() throws Exception {

		when(appointmentClient.viewAppointment(1)).thenReturn(null);
		this.mockMvc.perform(get("/viewappointment/1")).andExpect(status().isOk());
	}

	@Test
	public void viewAppointmentFailTest() throws Exception {
		when(appointmentClient.viewAppointment(1)).thenReturn(null);
		this.mockMvc.perform(get("/viewappointment/a")).andExpect(status().isBadRequest());
	}
}