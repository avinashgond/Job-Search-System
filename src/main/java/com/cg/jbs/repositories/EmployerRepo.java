package com.cg.jbs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.jbs.entities.Employer;

@Repository
public interface EmployerRepo extends JpaRepository<Employer, Integer>{
	Employer findByEmail(final String email);
}
