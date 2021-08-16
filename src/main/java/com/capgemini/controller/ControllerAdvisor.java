package com.capgemini.controller;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ControllerAdvisor {
	
	/**
	 * Handles any illegalargumentexception so the whole API doesn't blows up if anything
	 * goes wrong
	 * @param ex
	 * @param request
	 * @return
	 */
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request){
		Map<String,Object> body = new LinkedHashMap<>();
		body.put("timestamp",LocalDateTime.now());
		body.put("message", "Illegal argument entered");
		
		return new ResponseEntity<>(body,HttpStatus.NOT_FOUND);
		
	}

}
