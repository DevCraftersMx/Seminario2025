package com.seminario.tasks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seminario.tasks.model.TaskVO;

@Repository
public interface TaskRepository extends JpaRepository <TaskVO, Integer>{
  public List<TaskVO> getAll();
  public TaskVO findById(int id);
}
