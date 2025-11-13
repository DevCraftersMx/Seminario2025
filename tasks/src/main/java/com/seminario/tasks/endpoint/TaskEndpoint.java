package com.seminario.tasks.endpoint;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.seminario.tasks.Utils.ResponseBean;
import com.seminario.tasks.Utils.Utils;
import com.seminario.tasks.model.CategoriesVO;
import com.seminario.tasks.model.PriorityVO;
import com.seminario.tasks.model.StatusVO;
import com.seminario.tasks.model.TaskDTO;
import com.seminario.tasks.model.TaskVO;
import com.seminario.tasks.service.TaskService;


@RequestMapping("task")
@RestController
@CrossOrigin(origins = "*")
public class TaskEndpoint {
  private static final Logger LOG = LoggerFactory.getLogger(TaskEndpoint.class);

  @Autowired
  private TaskService taskService;

  @GetMapping("/findAllCategories")
  public ResponseEntity<ResponseBean<List<CategoriesVO>>> findAllCategories() {
    LOG.info("findAllCategoriesEndpoint - In");
    ResponseEntity<ResponseBean<List<CategoriesVO>>> response = null;
    List<CategoriesVO> categories = null;
    try {
      categories = taskService.getAllCategories();
      response = Utils.response200OK("Categorias encontradas", categories);
    } catch (Exception e) {
      response = Utils.handle(e, "Categorias no encontradas");
    }
    LOG.info("findAllCategoriesEndpoint - Out");
    return response;
  }
  
  @GetMapping("/findAllStatus")
  public ResponseEntity<ResponseBean<List<StatusVO>>> findAllStatus() {
    LOG.info("findAllStatusEndpoint - In");
    ResponseEntity<ResponseBean<List<StatusVO>>> response = null;
    List<StatusVO> status = null;
    try {
      status = taskService.getAllStatus();
      response = Utils.response200OK("Estados encontrados", status);
    } catch (Exception e) {
      response = Utils.handle(e, "Estados no encontrados");
    }
    LOG.info("findAllStatusEndpoint - Out");
    return response;
  }

  @GetMapping("/findAllPriorities")
  public ResponseEntity<ResponseBean<List<PriorityVO>>> findAllPriorities() {
    LOG.info("findAllPrioritiesEndpoint - In");
    ResponseEntity<ResponseBean<List<PriorityVO>>> response = null;
    List<PriorityVO> priorities = null;
    try {
      priorities = taskService.getAllPriorities();
      response = Utils.response200OK("Prioridades encontradas", priorities);
    } catch (Exception e) {
      response = Utils.handle(e, "Prioridades no encontradas");
    }
    LOG.info("findAllPrioritiesEndpoint - Out");
    return response;
  }

  @GetMapping("/findAllTasks")
  public ResponseEntity<ResponseBean<List<TaskVO>>> findAllTasks() {
    LOG.info("findAllTasksEndpoint - In");
    ResponseEntity<ResponseBean<List<TaskVO>>> response = null;
    List<TaskVO> tasks = null;
    try {
      tasks = taskService.getAllTasks();
      response = Utils.response200OK("Tareas encontradas", tasks);
    } catch (Exception e) {
      response = Utils.handle(e, "Tareas no encontradas");
    }
    LOG.info("findAllTasksEndpoint - Out");
    return response;
  }

  @PostMapping("/createTask")
  public ResponseEntity<ResponseBean<Void>> createTask(@RequestBody TaskDTO taskDTO) {
    LOG.info("createTaskEndpoint - In");
    ResponseEntity<ResponseBean<Void>> response = null;
    try {
      taskService.createTask(taskDTO);
      response = Utils.response200OK("Tarea creada exitosamente", null);
    } catch (Exception e) {
      response = Utils.handle(e, "Error al crear la tarea");
    }
    LOG.info("createTaskEndpoint - Out");
    return response;
  }

  @PostMapping("/updateTask")
  public ResponseEntity<ResponseBean<Void>> updateTask(@RequestParam int id, @RequestBody TaskDTO task) {
    LOG.info("updateTaskEndpoint - In");
    ResponseEntity<ResponseBean<Void>> response = null;
    try {
      taskService.updateTask(id, task);
      response = Utils.response200OK("Tarea actualizada exitosamente", null);
    } catch (Exception e) {
      response = Utils.handle(e, "Error al actualizar la tarea");
    }
    LOG.info("updateTaskEndpoint - Out");
    return response;
  }

  @GetMapping("/deleteTask")
  public ResponseEntity<ResponseBean<Void>> deleteTask(@RequestParam int id) {
    LOG.info("deleteTaskEndpoint - In");
    ResponseEntity<ResponseBean<Void>> response = null;
    try {
      taskService.deleteTask(id);
      response = Utils.response200OK("Tarea eliminada exitosamente", null);
    } catch (Exception e) {
      response = Utils.handle(e, "Error al eliminar la tarea");
    }
    LOG.info("deleteTaskEndpoint - Out");
    return response;
  }
}
