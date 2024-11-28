package com.example.doctormicroservices.model;

import java.time.LocalDate;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {

	private int appointmentId;
	private String patientName;
	private String doctorName;
	private LocalDate dateOfAppointment;
	private String timeSlot;
	private String status;
	private String remark;
	private int age;
	private String gender;
	private long contactNo;
}
