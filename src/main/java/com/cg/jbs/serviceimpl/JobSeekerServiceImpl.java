package com.cg.jbs.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cg.jbs.dto.JobSeekerDto;
import com.cg.jbs.entities.Employer;
import com.cg.jbs.entities.JobSeeker;
import com.cg.jbs.exception.JobSeekerEmailAlreadyRegistered;
import com.cg.jbs.repositories.EmployerRepo;
import com.cg.jbs.repositories.JobSeekerRepo;
import com.cg.jbs.services.JobSeekerService;

@Service
public class JobSeekerServiceImpl implements JobSeekerService{
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private JobSeekerRepo jobSeekerRepo;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private EmployerRepo employerRepo;
	
	@Override
	public JobSeekerDto addJobSeeker(JobSeekerDto jobSeekerDto) {
		JobSeeker jobSeeker = modelMapper.map(jobSeekerDto, JobSeeker.class);
		JobSeeker existingJobSeeker = jobSeekerRepo.findByEmail(jobSeeker.getEmail());
		Employer employer = employerRepo.findByEmail(jobSeeker.getEmail());
		if(existingJobSeeker == null & employer == null) {
			jobSeeker.setPassword(bCryptPasswordEncoder.encode(jobSeekerDto.getPassword()));
			jobSeekerRepo.save(jobSeeker);
			return modelMapper.map(jobSeeker, JobSeekerDto.class);
		}else {
			throw new JobSeekerEmailAlreadyRegistered("Email Id already registered");
		}
	}
}
