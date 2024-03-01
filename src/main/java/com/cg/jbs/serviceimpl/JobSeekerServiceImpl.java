package com.cg.jbs.serviceimpl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cg.jbs.dto.AppliedMessage;
import com.cg.jbs.dto.JobDto;
import com.cg.jbs.dto.JobSeekerDto;
import com.cg.jbs.entities.AppliedJobs;
import com.cg.jbs.entities.Basket;
import com.cg.jbs.entities.Employer;
import com.cg.jbs.entities.Job;
import com.cg.jbs.entities.JobSeeker;
import com.cg.jbs.entities.JobSeekerMessage;
import com.cg.jbs.exception.EmployerAlreadyExistException;
import com.cg.jbs.exception.EmployerNotFoundException;
import com.cg.jbs.exception.JobSeekerEmailAlreadyRegistered;
import com.cg.jbs.exception.NoAnyResultFoundException;
import com.cg.jbs.exception.ResourceNotFoundException;
import com.cg.jbs.helper.UserRole;
import com.cg.jbs.repositories.AppliedJobsRepo;
import com.cg.jbs.repositories.BasketRepo;
import com.cg.jbs.repositories.EmployerRepo;
import com.cg.jbs.repositories.JobRepo;
import com.cg.jbs.repositories.JobSeekerRepo;
import com.cg.jbs.services.JobSeekerService;

@Service
public class JobSeekerServiceImpl implements JobSeekerService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private JobSeekerRepo jobSeekerRepo;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private EmployerRepo employerRepo;

	@Autowired
	private JobRepo jobRepo;
	
	@Autowired
	private AppliedJobsRepo appliedJobsRepo;

	@Autowired
	private BasketRepo basketRepo;
	
	@Override
	public JobSeekerDto addJobSeeker(JobSeekerDto jobSeekerDto) {
		JobSeeker jobSeeker = modelMapper.map(jobSeekerDto, JobSeeker.class);
		boolean checkJobSeeker = jobSeekerRepo.findByEmail(jobSeeker.getEmail()).isPresent();
		boolean checkEmployer = employerRepo.findByEmail(jobSeeker.getEmail()).isPresent();
		if (checkEmployer)
			throw new EmployerAlreadyExistException("Email Id Already Registered !!");
		if (checkJobSeeker)
			throw new JobSeekerEmailAlreadyRegistered("Email id already registered!!");
		jobSeeker.setPassword(bCryptPasswordEncoder.encode(jobSeekerDto.getPassword()));
		jobSeeker.setRole(UserRole.ROLE_JOBSEEKER);
		jobSeekerRepo.save(jobSeeker);
		return modelMapper.map(jobSeeker, JobSeekerDto.class);
	}

	@Override
	public List<JobDto> searchJobBySkills(String keyword) {
		List<Job> allJobs = jobRepo.findByRequiredSkillsContainingIgnoreCase(keyword);
		if(allJobs.size()==0)
			 throw new NoAnyResultFoundException("No result found");
		return allJobs.stream().map(x -> modelMapper.map(x, JobDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<JobDto> searchJobByOrganization(String organization) {
		Employer emp = employerRepo.findByOrganizationNameContainingIgnoreCase(organization)
				.orElseThrow(() -> new EmployerNotFoundException("Not Found !!"));
		List<Job> allJobsByEmployer = jobRepo.findAllByEmployerId(emp.getId())
				.orElseThrow(() -> new EmployerNotFoundException("Not Found any employer!!"));
		if(allJobsByEmployer.size()==0)
			 throw new NoAnyResultFoundException("No result found");
		return allJobsByEmployer.stream().map(x->modelMapper.map(x, JobDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<JobDto> searchJobByLocation(String location) {
		List<Job> allJobs = jobRepo.findByLocationContainingIgnoreCase(location);
		if(allJobs.size()==0)
			 throw new NoAnyResultFoundException("No result found");
		return allJobs.stream().map(x->modelMapper.map(x, JobDto.class)).collect(Collectors.toList());
		
	}

	@Override
	public AppliedMessage applyJob(Integer jobId, Integer jsId) {
		AppliedMessage appliedMessage = new AppliedMessage();
		try {
			AppliedJobs appliedJobs = new AppliedJobs();
//			JobSeeker jobSeeker = jobSeekerRepo.findById(jsId).orElseThrow(()->new ResourceNotFoundException("Job Seeker Not Found!!"));
			JobSeeker jobSeeker = findJobSeeker(jsId);
			 appliedJobs.setJobId(jobId);
			appliedJobs.setJobSeeker(jobSeeker);
			appliedJobsRepo.save(appliedJobs);
			appliedMessage.setMessage("Thanks for apply !!");
			appliedMessage.setSuccessfull(true);
			return appliedMessage;
			
		}catch(final Exception e) {
			appliedMessage.setMessage(e.getMessage());
			appliedMessage.setSuccessfull(false);
			return appliedMessage;
		}
	}

	@Override
	public void addJobToBasket(Integer jobSeekerId, Integer jobId) {
		JobSeeker jobSeeker = jobSeekerRepo.findById(jobSeekerId).orElseThrow(()-> new ResourceNotFoundException("JobSeeker Details Not Found With This Id : " + jobSeekerId));
		Job job = jobRepo.findById(jobId).orElseThrow(()-> new ResourceNotFoundException("Job Details Not Found With This Id : " + jobId));
      Basket basket = jobSeeker.getBasket();
      if (basket == null) {
          basket = new Basket();
          basket.setJobSeeker(jobSeeker);
          jobSeeker.setBasket(basket);
      }
      basket.getJobs().add(job);
      basketRepo.save(basket);
	}

	@Override
	public List<JobDto> viewJobBasket(Integer jobSeekerId) {
//		JobSeeker jobSeeker = jobSeekerRepo.findById(jobSeekerId).orElseThrow(()-> new ResourceNotFoundException("Job Seeker Not Registered !!"));
		findJobSeeker(jobSeekerId);
		Basket basket = basketRepo.findByJobSeekerId(jobSeekerId).orElseThrow(()-> new ResourceNotFoundException("Basket Is Empty For Job Seeker : " + jobSeekerId));
		Set<Job> allJobs = basket.getJobs();
		return allJobs.stream().map(x->modelMapper.map(x, JobDto.class)).collect(Collectors.toList());
	}

	@Override
	public void removeJobFromBasket(Integer jobId, Integer jobSeekerId) {
		Basket basket = basketRepo.findByJobSeekerId(jobSeekerId).orElse(null);
		Set<Job> allJobsInBasket = basket.getJobs();
		allJobsInBasket.stream().forEach(x->{
			if(x.getId()==jobId) {
				allJobsInBasket.remove(x);
			}
		});
		basket.setJobs(allJobsInBasket);
		basketRepo.save(basket);
	}

	private JobSeeker findJobSeeker(Integer jobSeekerId) {
		return jobSeekerRepo.findById(jobSeekerId).orElseThrow(()-> new ResourceNotFoundException("Job Seeker Not Registered !!"));
	}

	@Override
	public void sendMessageToEmployer(Integer jobId, JobSeekerMessage jsMessage, Integer jobSeekerId) {
		Job job = jobRepo.findById(jobId).orElseThrow(()-> new ResourceNotFoundException("Job Id Not Found : " + jobId));
		Employer emp = job.getEmployer();
		
	}
}
