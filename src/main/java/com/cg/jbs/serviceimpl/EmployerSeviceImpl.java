package com.cg.jbs.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cg.jbs.dto.EmployerDto;
import com.cg.jbs.dto.JobDto;
import com.cg.jbs.dto.JobSeekerDto;
import com.cg.jbs.entities.Employer;
import com.cg.jbs.entities.Job;
import com.cg.jbs.entities.JobSeeker;
import com.cg.jbs.exception.EmployerAlreadyExistException;
import com.cg.jbs.exception.EmployerNotFoundException;
import com.cg.jbs.exception.JobIdNotFoundException;
import com.cg.jbs.exception.JobSeekerEmailAlreadyRegistered;
import com.cg.jbs.exception.OrganizationAlreadyRegistered;
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
	public EmployerDto addEmployer(final EmployerDto employerDto){
	        Employer employer = modelMapper.map(employerDto, Employer.class);
	        boolean checkEmployer = employerRepo.findByEmail(employer.getEmail()).isPresent();
	        boolean checkJobSeeker = jobSeekerRepo.findByEmail(employer.getEmail()).isPresent();
	        boolean checkOrganizationName = employerRepo.findByOrganizationNameContainingIgnoreCase(employer.getOrganizationName()).isPresent();
	        if (checkOrganizationName)
	            throw new OrganizationAlreadyRegistered("Organization already registered");
	        if (checkEmployer)
	            throw new EmployerAlreadyExistException("Email id already registered");
	        if (checkJobSeeker)
	            throw new JobSeekerEmailAlreadyRegistered("Email id already registered");

	        /* Set Encrypted Password */
	        employer.setPassword(bCryptPasswordEncoder.encode(employer.getPassword()));
	        /* Set Role */
	        employer.setRole(UserRole.ROLE_EMPLOYER);
	        employerRepo.save(employer);
	        return modelMapper.map(employer, EmployerDto.class);
	}

	@Override
	public JobDto postJob(JobDto jobDto, Integer empId) {
		Job job = modelMapper.map(jobDto, Job.class);
		Employer emp = employerRepo.findById(empId)
				.orElseThrow(() -> new EmployerNotFoundException("Employer Id invalid!"));
		job.setDate(new Date());
		job.setEmployer(emp);
		Job addedJob = jobRepo.save(job);
		return modelMapper.map(addedJob, JobDto.class);
	}

	@Override
	public JobDto updateJobDetails(JobDto jobDto, Integer jobId) {
		Job existingJob = jobRepo.findById(jobId).orElseThrow(() -> new JobIdNotFoundException("Invalid Job Id!!"));
		existingJob.setContactEmail(jobDto.getContactEmail());
		existingJob.setDate(new Date());
		existingJob.setDescription(jobDto.getDescription());
//		existingJob.setEmployer(existingJob.getEmployer());
//		existingJob.setJobSeeker(existingJob.getJobSeeker());
		existingJob.setExperience(jobDto.getExperience());
		existingJob.setJobTitile(jobDto.getJobTitile());
		existingJob.setLocation(jobDto.getLocation());
		existingJob.setNoticePeriod(jobDto.getNoticePeriod());
		existingJob.setRequiredSkills(jobDto.getRequiredSkills());
		existingJob.setSalary(jobDto.getSalary());
		existingJob.setStatus(jobDto.getStatus());
		jobRepo.save(existingJob);
		return modelMapper.map(existingJob, JobDto.class);
	}

	@Override
	public void deleteJobByJobId(Integer jobId) {
		Job existingJob = jobRepo.findById(jobId).orElseThrow(() -> new JobIdNotFoundException("Invalid Job Id!!"));
		jobRepo.delete(existingJob);
	}

	@Override
	public List<JobDto> viewAllJobsByEmpId(Integer empId) {
		List<Job> allJobs = jobRepo.findAllByEmployerId(empId)
				.orElseThrow(() -> new EmployerNotFoundException("Employer id is invalid!!"));
		List<JobDto> allJobsDto = allJobs.stream().map(x -> modelMapper.map(x, JobDto.class))
				.collect(Collectors.toList());
		return allJobsDto;
	}

	@Override
	public List<JobSeekerDto> searchJobSeekersByTheirSkillSets(String skillSets) {
		List<JobSeeker> allJobSeekersWithTheSkillSets = jobSeekerRepo.findAllBySkillSets(skillSets);
		return allJobSeekersWithTheSkillSets.stream().map(x -> modelMapper.map(x, JobSeekerDto.class))
				.collect(Collectors.toList());
	}
}
