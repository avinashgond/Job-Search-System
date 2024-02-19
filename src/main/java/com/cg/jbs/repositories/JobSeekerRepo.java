package com.cg.jbs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.jbs.entities.JobSeeker;

/**
 * Repository to use data base related operations
 */
@Repository
public interface JobSeekerRepo extends JpaRepository<JobSeeker, Integer>{
	
	/**
	 * This method use to find the job seeker details by using email id
	 * @param email -- contains email id
	 * @return JobSeeker -- will return JobSeeker
	 */
	JobSeeker findByEmail(final String email);

}
