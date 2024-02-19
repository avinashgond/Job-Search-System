package com.cg.jbs.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cg.jbs.config.AppConstant;
import com.cg.jbs.dto.EmployerDto;
import com.cg.jbs.entities.Employer;
import com.cg.jbs.entities.JobSeeker;
import com.cg.jbs.entities.Roles;
import com.cg.jbs.exception.EmployerAlreadyExistException;
import com.cg.jbs.exception.EmployerNotFoundException;
import com.cg.jbs.repositories.EmployerRepo;
import com.cg.jbs.repositories.JobSeekerRepo;
import com.cg.jbs.services.EmployerService;

@Service
public class EmployerSeviceImpl implements EmployerService{
	
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

	@Override
	public EmployerDto addEmployer(final EmployerDto employerDto) {
		Employer employer = modelMapper.map(employerDto, Employer.class);
		Employer existingEmployer = employerRepo.findByEmail(employer.getEmail());
		JobSeeker jobSeeker = jobSeekerRepo.findByEmail(employer.getEmail());
		if(existingEmployer == null & jobSeeker == null) {
			employer.setPassword(bCryptPasswordEncoder.encode(employer.getPassword()));
			employerRepo.save(employer);
			return modelMapper.map(employer, EmployerDto.class);
			
		}else {
			throw new EmployerAlreadyExistException("Email id already registered");
		}
	
	}

}
