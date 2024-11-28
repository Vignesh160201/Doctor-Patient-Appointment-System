package com.example.doctormicroservices.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import com.example.doctormicroservices.entity.Doctor;
import com.example.doctormicroservices.service.DoctorService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@SuppressWarnings("unused")
class DoctorControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	DoctorService doctorService;

	
	@Autowired
	private WebApplicationContext context;
	ObjectMapper objectMapper = new ObjectMapper();
	

	LocalDate date = LocalDate.now();

	@Test
	public void testgetDistinctSpecialization() throws Exception {
		this.mockMvc.perform(get("/distinctspecialization")).andExpect(status().isOk());
	}

	@Test
	public void testgetDoctorBySpecialist() throws Exception {
		this.mockMvc.perform(get("/getdoctorbyspecialist/General")).andExpect(status().isOk());
	}

	@Test
	public void testgetDoctorById() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.get("/getdoctor/vignesh")
				.header("Authorization","@Authorization"))
				.andExpect(status().isOk());
	}

	@Test
	public void testupdateDoctor() throws Exception {
		Doctor doctor = new Doctor("Vignesh", "MNND", "General", "New", "9874561");
	String jsonRequest = objectMapper.writeValueAsString(doctor);
		this.mockMvc.perform(put("/updatedoctor/vignesh").header("Authorization","@Authorization")
				.content(jsonRequest)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
		        .andExpect(status().isOk());

	}

	@Test
	public void testupdateStatus() throws Exception {
	this.mockMvc.perform(MockMvcRequestBuilders.put("/updatestatus/1/done/twopills").header("Authorization", "@Authorization"))
		.andExpect(status().isOk());
	}

}
