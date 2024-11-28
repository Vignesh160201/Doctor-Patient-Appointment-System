package com.example.appointmentmicroservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.appointmentmicroservices.entity.Appointment;

import feign.Param;

@Repository
public interface AppointmentRepository  extends JpaRepository<Appointment, Integer>{
	
	@Query(value="SELECT a FROM Appointment a WHERE a.doctorName=:doctorName order by dateOfAppointment")
	public List<Appointment> getAllAppointments(@Param String doctorName);
}
