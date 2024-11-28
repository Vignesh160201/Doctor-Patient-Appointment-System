package com.example.appointmentmicroservices.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.example.appointmentmicroservices.model.Patient;
import com.example.appointmentmicroservices.service.AppointmentService;
import com.fasterxml.jackson.databind.ObjectMapper;



@SpringBootTest
@AutoConfigureMockMvc
class AppointmentControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;

	ObjectMapper objectMapper = new ObjectMapper();
	

	
	@Autowired
	AppointmentService appointmentService;

	@Test
	public void testMockMvc() {
		assertNotNull(mockMvc);
	}
	@Test
	public void makeAppointmentTest() throws Exception {

		Patient patient = new Patient();
		patient.setPatientName("Rithika");
		patient.setAge(12);
		patient.setContactNo(98975612);
		
		patient.setGender("Female");
		patient.setDoctorId("Vignesh");
		patient.setSpecialization("General");
		patient.setTimeSlot("2pm to 3pm");
		String jsonRequest=objectMapper.writeValueAsString(patient);
		
				this.mockMvc.perform(post("/makeappointment")
						.content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
						.andExpect(status().isOk());
	}

	@Test
	public void makeAppointmentFailTest() throws Exception {

		Patient patient = new Patient();
		patient.setPatientName("Rithika");
		patient.setAge(12);
		patient.setContactNo(98975612);
		
		patient.setGender("Female");
		patient.setDoctorId("Vignesh");
		patient.setSpecialization("General");
		patient.setTimeSlot("2pm to 3pm");
		String jsonRequest=objectMapper.writeValueAsString(patient);
		
				this.mockMvc.perform(post("/makeappointment")
						.content(jsonRequest).contentType(MediaType.TEXT_PLAIN))
						.andExpect(status().isUnsupportedMediaType());
	}
	
	@Test
	public void viewAppointmentTest() throws Exception {
		this.mockMvc.perform(get("/viewappointment/1").contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk());
	}	
	@Test
	public void viewAppointmentExceptionTest() throws Exception {
		this.mockMvc.perform(get("/viewappointment/A").contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isBadRequest());
	}
	
	@Test 
	public void updateStatusTest() throws Exception {
		this.mockMvc.perform(put("/updatestatus/1/done/twopills"))
	.andExpect(status().isNotFound());
	}
	@Test 
	public void updateStatusTestNO() throws Exception {
		this.mockMvc.perform(put("/updatestatus/1/done/twopills").contentType(MediaType.APPLICATION_JSON_VALUE))
	.andExpect(status().isOk());
	}
	@Test
	public void getallAppointmentTest() throws Exception {
		this.mockMvc.perform(get("/getallappointment/vignesh").contentType(MediaType.APPLICATION_JSON_VALUE))
		.andExpect(status().isOk());

	}


}
