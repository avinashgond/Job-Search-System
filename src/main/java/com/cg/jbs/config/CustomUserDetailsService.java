package com.cg.jbs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cg.jbs.entities.Employer;
import com.cg.jbs.entities.JobSeeker;
import com.cg.jbs.repositories.EmployerRepo;
import com.cg.jbs.repositories.JobSeekerRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	
	@Autowired
    private EmployerRepo employerRepo;
	
	@Autowired
	private JobSeekerRepo jobSeekerRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employer employer = employerRepo.findByEmail(username);
        JobSeeker jobSeeker = jobSeekerRepo.findByEmail(username);
        if(employer !=null) {
        	return employer;
        }else {
        	return jobSeeker;
        }
    }
}

