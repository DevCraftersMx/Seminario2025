package com.seminario.tasks.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seminario.tasks.Utils.Utils;
import com.seminario.tasks.Utils.exception.AppException;
import com.seminario.tasks.model.CategoriesVO;
import com.seminario.tasks.model.PriorityVO;
import com.seminario.tasks.model.StatusVO;
import com.seminario.tasks.model.TaskDTO;
import com.seminario.tasks.model.TaskVO;
import com.seminario.tasks.repository.CategoriesRepository;
import com.seminario.tasks.repository.PriorityRepository;
import com.seminario.tasks.repository.StatusRepository;
import com.seminario.tasks.repository.TaskRepository;
import com.seminario.tasks.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {
  private static final Logger LOG = LoggerFactory.getLogger(TaskService.class);

  @Autowired
  private TaskRepository taskRepository;

  @Autowired
  private CategoriesRepository categoriesRepository;

  @Autowired
  private PriorityRepository priorityRepository;

  @Autowired
  private StatusRepository statusRepository;  

  @Override
  public List<CategoriesVO> getAllCategories() throws AppException {
    List<CategoriesVO> categories = null;
    try {
      categories = categoriesRepository.findAll();
    } catch (Exception e) {
      Utils.raise(e, "Error no se pudo traer los datos");
    }
    return categories;
  }

  @Override
  public List<StatusVO> getAllStatus() throws AppException {
    List<StatusVO> status = null;
    try {
      status = statusRepository.findAll();
    } catch (Exception e) {
      Utils.raise(e, "Error no se pudo traer los datos");
    }
    return status;
  }

  @Override
  public List<PriorityVO> getAllPriorities() throws AppException {
    List<PriorityVO> priorities = null;
    try {
      priorities = priorityRepository.findAll();
    } catch (Exception e) {
      Utils.raise(e, "Error no se pudo traer los datos");
    }
    return priorities;
  }

  @Override
  public List<TaskVO> getAllTasks() throws AppException {
    List<TaskVO> tasks = null;
    try {
      tasks = taskRepository.getAll();
      if (tasks == null || tasks.isEmpty()) {
        throw new AppException("No hay tareas registradas");
      }
    } catch (Exception e) {
      Utils.raise(e, "Error no se pudo traer los datos");
    }
    return tasks;
  }

  @Override
  public void createTask(TaskDTO task) throws AppException {
    TaskVO newTask = new TaskVO();
    try {
      newTask.setName(task.getName());
      newTask.setDescription(task.getDescription());

      CategoriesVO category = categoriesRepository.findById(task.getId_category());
      newTask.setId_category(category);

      StatusVO status = statusRepository.findById(task.getId_status());
      newTask.setId_status(status);

      PriorityVO priority = priorityRepository.findById(task.getId_priority());
      newTask.setId_priority(priority);

      newTask.setStatus(1);
      newTask.setCreated_at(LocalDateTime.now());
      newTask.setUpdated_at(LocalDateTime.now());

      taskRepository.save(newTask);
    } catch (Exception e) {
      Utils.raise(e, "Error no se pudo traer los datos");
    }
  }

  @Override
  public void updateTask(int id, TaskDTO task) throws AppException {
    TaskVO existingTask = null;
    try {
      existingTask = taskRepository.findById(id);
      if (existingTask == null) {
        throw new AppException("La tarea no existe");
      }

      if (existingTask.getStatus() == 0) {
        throw new AppException("No se puede actualizar una tarea eliminada");
      }

      existingTask.setName(task.getName());
      existingTask.setDescription(task.getDescription());

      CategoriesVO category = categoriesRepository.findById(task.getId_category());
      existingTask.setId_category(category);

      StatusVO status = statusRepository.findById(task.getId_status());
      existingTask.setId_status(status);

      PriorityVO priority = priorityRepository.findById(task.getId_priority());
      existingTask.setId_priority(priority);

      existingTask.setUpdated_at(LocalDateTime.now());
      taskRepository.save(existingTask);
    } catch (Exception e) {
      Utils.raise(e, "Error no se pudo traer los datos");
    }
  }

  @Override
  public void deleteTask(int taskId) throws AppException {
    TaskVO existingTask = null;
    try {
      existingTask = taskRepository.findById(taskId);
      if (existingTask == null) {
        throw new AppException("La tarea no existe");
      }

      if (existingTask.getStatus() == 0) {
        throw new AppException("La tarea ya fue eliminada");
      }

      existingTask.setStatus(0);
      existingTask.setUpdated_at(LocalDateTime.now());
      taskRepository.save(existingTask);
    } catch (Exception e) {
      Utils.raise(e, "Error no se pudo traer los datos");
    }
  }
}
