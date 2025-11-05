package com.seminario.tasks.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "priority")
@NamedQueries({
  @NamedQuery(name = "PriorityVO.findAll", query = "SELECT c FROM PriorityVO c WHERE c.status = 1"),
})
public class PriorityVO {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int id;
  String name;
  int status;
}
