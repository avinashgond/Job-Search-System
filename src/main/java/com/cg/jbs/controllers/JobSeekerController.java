package com.cg.jbs.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.jbs.dto.AppliedMessage;
import com.cg.jbs.dto.JobDto;
import com.cg.jbs.dto.JobSeekerMessageDto;
import com.cg.jbs.entities.JobSeekerMessage;
import com.cg.jbs.services.JobSeekerService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/jobseeker")
public class JobSeekerController {

	@Autowired
	private JobSeekerService jobSeekerService;

	@GetMapping("/search-job-by-skills")
	public ResponseEntity<List<JobDto>> searchJobBySkills(@RequestParam String skills) {
		List<JobDto> allJobs = jobSeekerService.searchJobBySkills(skills);
		return new ResponseEntity<List<JobDto>>(allJobs, HttpStatus.OK);
	}

	@GetMapping("/search-job-by-organization")
	public ResponseEntity<List<JobDto>> searchJobByOrganization(@RequestParam String organization) {
		List<JobDto> allJobs = jobSeekerService.searchJobByOrganization(organization);
		return new ResponseEntity<List<JobDto>>(allJobs, HttpStatus.OK);
	}

	@GetMapping("/search-job-by-location")
	public ResponseEntity<List<JobDto>> searchJobByLocation(@RequestParam String location) {
		List<JobDto> allJobs = jobSeekerService.searchJobByLocation(location);
		return new ResponseEntity<List<JobDto>>(allJobs, HttpStatus.OK);
	}

	@PostMapping("/apply-job/jobId/{jobId}/jobseekerId/{jsId}")
	public ResponseEntity<AppliedMessage> applyJob(@PathVariable Integer jobId, @PathVariable Integer jsId) {
		AppliedMessage response = jobSeekerService.applyJob(jobId, jsId);
		return new ResponseEntity<AppliedMessage>(response, HttpStatus.CREATED);

	}

	@PostMapping("/add-job-to-basket/jobId/{jobId}/jobSeekerId/{jsId}")
	public ResponseEntity<String> addJobToBasket(@PathVariable Integer jobId, @PathVariable Integer jsId) {
		jobSeekerService.addJobToBasket(jsId, jobId);
		return ResponseEntity.status(HttpStatus.CREATED).body("Job added to basket successfully");
	}
	
	@GetMapping("/view-job-basket/jobSeekerId/{jobSeekerId}")
	public ResponseEntity<List<JobDto>> viewJobBasket(@PathVariable Integer jobSeekerId){
		List<JobDto> allJobs = jobSeekerService.viewJobBasket(jobSeekerId);
		return new ResponseEntity<List<JobDto>>(allJobs, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete-job-from-basket/jobId/{jobId}/jobSeekerId/{jobSeekerId}")
	public ResponseEntity<String> removeJobFromBasket(@PathVariable Integer jobId, @PathVariable Integer jobSeekerId){
		jobSeekerService.removeJobFromBasket(jobId, jobSeekerId);
		return new ResponseEntity<String>("Successfully Removed !!", HttpStatus.OK);
	}
	
//	@PostMapping("/send-message-to-employer/jobId/{jobId}/jobSeekerId/{jobSeekerId}")
//	public ResponseEntity<JobSeekerMessage> sendMessageToEmployerAgainstJobId(@Valid @RequestBody JobSeekerMessageDto jobSeekerMessage,@PathVariable Integer jobId, @PathVariable Integer jobSeekerId){
//		jobSeekerService.sendMessageToEmployer(jobId, jobSeekerMessage, jobSeekerId);
//		return new ResponseEntity<JobSeekerMessage>();
//	}
}
