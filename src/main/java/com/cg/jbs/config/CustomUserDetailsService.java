package com.cg.jbs.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cg.jbs.entities.Employer;
import com.cg.jbs.entities.JobSeeker;
import com.cg.jbs.exception.ResourceNotFoundException;
import com.cg.jbs.helper.UserRole;
import com.cg.jbs.repositories.EmployerRepo;
import com.cg.jbs.repositories.JobSeekerRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployerRepo employerRepo;

    @Autowired
    private JobSeekerRepo jobSeekerRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employer employer = employerRepo.findByEmail(email).orElse(null);
        JobSeeker jobSeeker = jobSeekerRepo.findByEmail(email).orElse(null);
        if (employer != null) {
            return new org.springframework.security.core.userdetails.User(employer.getEmail(), employer.getPassword(),
                    getAuthorities(employer.getRole()));
        } else if (jobSeeker != null) {
            return new org.springframework.security.core.userdetails.User(jobSeeker.getEmail(), jobSeeker.getPassword(),
                    getAuthorities(jobSeeker.getRole()));
        } else {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
    }

    private Collection<GrantedAuthority> getAuthorities(UserRole role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
    }
}