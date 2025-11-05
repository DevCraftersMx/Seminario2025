package com.seminario.tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seminario.tasks.model.PriorityVO;

@Repository
public interface PriorityRepository extends JpaRepository<PriorityVO, Integer>{
  PriorityVO findById(int id);
}
