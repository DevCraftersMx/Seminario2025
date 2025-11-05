package com.seminario.tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seminario.tasks.model.CategoriesVO;


@Repository
public interface CategoriesRepository extends JpaRepository<CategoriesVO, Integer>{
  CategoriesVO findById(int id);
}
