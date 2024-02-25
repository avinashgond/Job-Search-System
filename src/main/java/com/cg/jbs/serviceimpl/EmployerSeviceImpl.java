package com.cg.jbs.serviceimpl;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cg.jbs.dto.EmployerDto;
import com.cg.jbs.dto.JobDto;
import com.cg.jbs.entities.Employer;
import com.cg.jbs.entities.Job;
import com.cg.jbs.entities.JobSeeker;
import com.cg.jbs.exception.EmployerAlreadyExistException;
import com.cg.jbs.exception.EmployerNotFoundException;
import com.cg.jbs.helper.UserRole;
import com.cg.jbs.repositories.EmployerRepo;
import com.cg.jbs.repositories.JobRepo;
import com.cg.jbs.repositories.JobSeekerRepo;
import com.cg.jbs.services.EmployerService;

@Service
public class EmployerSeviceImpl implements EmployerService {

	Logger logger = LoggerFactory.getLogger(EmployerSeviceImpl.class);

	/**
	 * modelMapper -- reference variable of model mapper
	 */
	@Autowired
	private ModelMapper modelMapper;

	/**
	 * employerRepo -- reference variable for employerRepo
	 */
	@Autowired
	private EmployerRepo employerRepo;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private JobSeekerRepo jobSeekerRepo;

	@Autowired
	private JobRepo jobRepo;

	@Override
	public EmployerDto addEmployer(final EmployerDto employerDto) {
		Employer employer = modelMapper.map(employerDto, Employer.class);
		Employer existingEmployer = employerRepo.findByEmail(employer.getEmail());
		JobSeeker jobSeeker = jobSeekerRepo.findByEmail(employer.getEmail());
		if (existingEmployer == null & jobSeeker == null) {
			employer.setPassword(bCryptPasswordEncoder.encode(employer.getPassword()));
			employer.setRole(UserRole.ROLE_EMPLOYER);
			employerRepo.save(employer);
			return modelMapper.map(employer, EmployerDto.class);

		} else {
			throw new EmployerAlreadyExistException("Email id already registered");
		}

	}

	@Override
	public JobDto postJob(JobDto jobDto, Integer empId) {
		Job job = modelMapper.map(jobDto, Job.class);
		Employer emp = employerRepo.findById(empId).orElseThrow(()-> new EmployerNotFoundException("Employer Id invalid!"));
		job.setDate(new Date());
		job.setEmployer(emp);
		Job addedJob = jobRepo.save(job);
		return modelMapper.map(addedJob, JobDto.class);
	}

//	@Override
//	public JobDto postJob(JobDto jobDto, Integer empId) {
//		Job job = modelMapper.map(jobDto, Job.class);
//		Employer emp = employerRepo.findById(empId).orElseThrow(()->  new EmployerNotFoundException("Employer Id is invalid"));
//		job.setEmployer(emp);
//		job.setDate(new Date());
//		Job addedJobDetails = jobRepo.save(job);
//		return modelMapper.map(addedJobDetails, JobDto.class);
//	}
}
