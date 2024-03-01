package com.cg.jbs.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.jbs.dto.JobDto;
import com.cg.jbs.dto.JobSeekerDto;
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
	
	/**
	 * This EndPoint to use post the job
	 * @param jobDto -- contains job details
	 * @param empId -- contains employer id
	 * @return ResponseEntity<JobDto> -- will return Job details
	 */
	@PostMapping("/post-job")
	@PreAuthorize("hasRole('EMPLOYER')")
	public ResponseEntity<JobDto> postJob(@Valid @RequestBody JobDto jobDto, @RequestParam Integer empId){
		JobDto postedJob =  employerService.postJob(jobDto, empId);
		return new ResponseEntity<JobDto>(postedJob,HttpStatus.CREATED);
	}
	
	/**
	 * This EndPoint use to update job details
	 * @param jobDto -- contains jobDto
	 * @param jobId -- contains Job Id
	 * @return ResponseEntity<JobDto> -- will return JobDto
	 */
	@PutMapping("/update-job")
	@PreAuthorize("hasRole('EMPLOYER')")
	public ResponseEntity<JobDto> updateJobDetails(@Valid @RequestBody JobDto jobDto, @RequestParam Integer jobId){
		JobDto updatedJobDetails = employerService.updateJobDetails(jobDto, jobId);
		return new ResponseEntity<JobDto>(updatedJobDetails,HttpStatus.OK);
	}
	
	/**
	 * This EndPoint use to delete job by given job id
	 * @param jobId -- contains jobId
	 * @return ResponseEntity<String> -- will return delete message
	 */
	@DeleteMapping("/delete-job")
	@PreAuthorize("hasRole('EMPLOYER')")
	public ResponseEntity<String> deleteJob(@Valid @RequestParam Integer jobId){
		employerService.deleteJobByJobId(jobId);
		return new ResponseEntity<String>("Successfully Deleted !!", HttpStatus.OK);
	}
	
	/**
	 * This EndPoint use to view all jobs
	 * @param empId -- contains empId
	 * @return ResponseEntity<List<JobDto>> -- will return list of job dto
	 */
	@GetMapping("/view-all-jobs/{empId}")
	public ResponseEntity<List<JobDto>> viewAllJobs(@PathVariable Integer empId){
		List<JobDto> allJobs = employerService.viewAllJobsByEmpId(empId);
		return new ResponseEntity<List<JobDto>>(allJobs,HttpStatus.OK);
	}
	
	/**
	 * This EndPoint use to search all job seekers by their skill sets
	 * @param skillSets -- contains skill sets
	 * @return ResponseEntity<List<JobSeekerDto>> -- will return List of JobSeekerDto
	 */
	@GetMapping("/search-jobseekers-by-skill-sets")
	public ResponseEntity<List<JobSeekerDto>> searchJobSeekersBySkillSet(@RequestParam String skillSets){
		List<JobSeekerDto> allJobs = employerService.searchJobSeekersByTheirSkillSets(skillSets);
		return new ResponseEntity<List<JobSeekerDto>>(allJobs, HttpStatus.OK);
	}
}
