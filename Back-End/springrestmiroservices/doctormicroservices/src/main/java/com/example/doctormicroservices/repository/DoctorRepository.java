package com.example.doctormicroservices.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.doctormicroservices.entity.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, String> {

	List<Doctor> findByDoctorSpecialist(String specialist);


	@Query(value="select distinct doctorSpecialist from Doctor d")
	List<String> findDistinctDoctorSpecialist();


}
