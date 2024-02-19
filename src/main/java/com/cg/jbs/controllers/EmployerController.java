package com.cg.jbs.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.jbs.services.EmployerService;

@RestController
@RequestMapping("/employer")
public class EmployerController {
	
	/**
	 * employerService -- reference variable of EmployerService
	 */
	@Autowired
	private EmployerService employerService;

}
