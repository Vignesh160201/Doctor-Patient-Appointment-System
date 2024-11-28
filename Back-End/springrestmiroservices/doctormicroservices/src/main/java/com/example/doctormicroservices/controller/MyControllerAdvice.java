package com.example.doctormicroservices.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.doctormicroservices.exception.AppointmentNotFoundException;
import com.example.doctormicroservices.exception.AuthorizationException;
import com.example.doctormicroservices.exception.DoctorNotFoundException;

import com.example.doctormicroservices.model.ErrorResponse;


@RestControllerAdvice
public class MyControllerAdvice extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(AppointmentNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleAppointmentNotFoundException(AppointmentNotFoundException e) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);	
	}
	
	@ExceptionHandler(DoctorNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleDoctorNotFoundException(DoctorNotFoundException e) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);	
	}
	

	
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<ErrorResponse> handalAuthorizationException(AuthorizationException e) {
		ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);	
	}
	
}
