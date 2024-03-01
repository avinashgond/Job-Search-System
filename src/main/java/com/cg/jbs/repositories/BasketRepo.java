package com.cg.jbs.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.jbs.entities.Basket;

@Repository
public interface BasketRepo extends JpaRepository<Basket, Integer>{
	
	Optional<Basket> findByJobSeekerId(final Integer jobSeekerId);
	
//	Optional<Basket> findByJobId(final Integer jobId);

}
