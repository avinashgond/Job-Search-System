package com.cg.jbs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.jbs.entities.AppliedJobs;

@Repository
public interface AppliedJobsRepo extends JpaRepository<AppliedJobs, Integer>{

}
