package com.seminario.tasks.service;

import java.util.List;

import com.seminario.tasks.Utils.exception.AppException;
import com.seminario.tasks.model.CategoriesVO;
import com.seminario.tasks.model.PriorityVO;
import com.seminario.tasks.model.StatusVO;
import com.seminario.tasks.model.TaskDTO;
import com.seminario.tasks.model.TaskVO;

public interface TaskService {
  public List<CategoriesVO> getAllCategories() throws AppException;
  public List<StatusVO> getAllStatus() throws AppException;
  public List<PriorityVO> getAllPriorities() throws AppException;

  public List<TaskVO> getAllTasks() throws AppException;
  public void createTask(TaskDTO task) throws AppException;
  public void updateTask(int id, TaskDTO task) throws AppException;
  public void deleteTask(int taskId) throws AppException;
}
