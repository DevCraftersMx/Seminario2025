package com.seminario.tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seminario.tasks.model.StatusVO;

@Repository
public interface StatusRepository extends JpaRepository<StatusVO, Integer>{
  StatusVO findById(int id);
}
