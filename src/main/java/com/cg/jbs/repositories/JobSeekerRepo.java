package com.cg.jbs.repositories;

import java.util.List;
import java.util.Optional;

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
	 * @return Optional<JobSeeker> -- will return JobSeeker
	 */
	Optional<JobSeeker> findByEmail(final String email);
	
	/**
	 * This method to use search all job seekers by their skill sets
	 * @param skillSets -- contains skill sets keyword
	 * @return List<JobSeeker> -- will return list of job seekers
	 */
	List<JobSeeker> findAllBySkillSets(final String skillSets);
}
