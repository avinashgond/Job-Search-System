package com.cg.jbs.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.jbs.entities.Job;

@Repository
public interface JobRepo extends JpaRepository<Job, Integer>{

	Optional<List<Job>> findAllByEmployerId(final Integer empId);
	
	List<Job> findByRequiredSkillsContainingIgnoreCase(String skillSets);
	
	List<Job> findByLocationContainingIgnoreCase(final String location);
}
