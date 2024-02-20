package com.cg.jbs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.jbs.entities.Job;

@Repository
public interface JobRepo extends JpaRepository<Job, Integer>{

}
