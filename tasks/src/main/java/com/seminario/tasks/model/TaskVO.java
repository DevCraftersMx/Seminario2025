package com.seminario.tasks.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "task")
@NamedQueries({
  @NamedQuery(name = "TaskVO.getAll", query = "SELECT c FROM TaskVO c WHERE c.status = 1"),
})
public class TaskVO implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int id;
  String name;
  String description;

  @ManyToOne
  @JoinColumn(name = "id_category", referencedColumnName = "id")
  CategoriesVO id_category;

  @ManyToOne
  @JoinColumn(name = "id_status", referencedColumnName = "id")
  StatusVO id_status;

  @ManyToOne
  @JoinColumn(name = "id_priority", referencedColumnName = "id")
  PriorityVO id_priority;
  
  int status;
  LocalDateTime created_at;
  LocalDateTime updated_at;
}
