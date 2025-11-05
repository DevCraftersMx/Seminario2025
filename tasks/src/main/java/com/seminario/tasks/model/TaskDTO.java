package com.seminario.tasks.model;

import lombok.Data;

@Data
public class TaskDTO {
  String name;
  String description;
  int id_category;
  int id_status;
  int id_priority;
}
