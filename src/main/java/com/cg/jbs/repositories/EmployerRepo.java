package com.cg.jbs.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.jbs.entities.Employer;

@Repository
public interface EmployerRepo extends JpaRepository<Employer, Integer>{
	Optional<Employer> findByEmail(final String email);
	Optional<Employer> findByOrganizationNameContainingIgnoreCase(final String organizationName);
}
