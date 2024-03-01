package com.cg.jbs.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.jbs.dto.EmployerDto;
import com.cg.jbs.dto.JobSeekerDto;
import com.cg.jbs.dto.JwtRequest;
import com.cg.jbs.dto.JwtResponse;
import com.cg.jbs.security.JwtHelper;
import com.cg.jbs.services.EmployerService;
import com.cg.jbs.services.JobSeekerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;
    
    @Autowired
    private EmployerService employerService;


    @Autowired
    private JwtHelper helper;
    
    @Autowired
    private JobSeekerService jobSeekerService;

    /**
     * logger -- This is use to log
     */
    private Logger logger = LoggerFactory.getLogger(AuthController.class);

    /**
     * This method use to register new employer
     * @param employerDto -- contains employer details
     * @return ResponseEntity<String> -- will return new Employer details
     */
    @PostMapping("/signup/employer")
	public ResponseEntity<EmployerDto> createEmployer(@Valid @RequestBody EmployerDto employerDto){
		EmployerDto empDto = employerService.addEmployer(employerDto);
		return new ResponseEntity<EmployerDto>(empDto, HttpStatus.CREATED);
	}
    
    /**
     * This method use to register the new job seeker
     * @param jobSeekerDto -- contains job seeker details
     * @return JobSeekerDto -- will return JobSeekerDto
     */
    @PostMapping("/signup/job-seeker")
	public ResponseEntity<JobSeekerDto> createJobSeeker(@Valid @RequestBody JobSeekerDto jobSeekerDto){
		JobSeekerDto jobSDto = jobSeekerService.addJobSeeker(jobSeekerDto);
		return new ResponseEntity<JobSeekerDto>(jobSDto, HttpStatus.CREATED);
	}

    /**
     * This method use to login and generate the token
     * @param request -- contains JWT request
     * @return ResponseEntity<JwtResponse> -- will return JWT Response
     */
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        this.doAuthenticate(request.getEmail(), request.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder()
                .token(token).build();
        logger.info("Successfully logged in!!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * This method use to authenticate using email and password
     * @param email -- contains email
     * @param password -- contains password
     */
    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }
}

