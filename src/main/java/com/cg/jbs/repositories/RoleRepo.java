package com.cg.jbs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.jbs.entities.Roles;

@Repository
public interface RoleRepo extends JpaRepository<Roles, Integer>{

}
