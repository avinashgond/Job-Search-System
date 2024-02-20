package com.cg.jbs.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.jbs.dto.JobDto;
import com.cg.jbs.services.EmployerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employer")
public class EmployerController {
	
	/**
	 * employerService -- reference variable of EmployerService
	 */
	@Autowired
	private EmployerService employerService;
	
	@PostMapping("/post-job")
	public ResponseEntity<JobDto> postJob(@Valid @RequestBody JobDto jobDto){
		JobDto postedJob =  employerService.postJob(jobDto);
		return new ResponseEntity<>(postedJob,HttpStatus.CREATED);
	}
}
