package br.com.hecherat.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "tb_tasks")
public class TaskModel {

  // Primary key
  @Id
  // Responsável por gerar automaticamente o id no DB
  @GeneratedValue(generator = "UUID")
  private UUID id;

  // Adiciona automaticamente a data de criação no DB
  @CreationTimestamp
  private LocalDateTime createdAt;

  private String description;
  private LocalDateTime endAt;
  private String priority;
  private LocalDateTime startAt;

  // Limita o tamanho de caracteres
  @Column(length = 50)
  private String title;

  private UUID userId;

  public void setTitle(String title) throws Exception {
    if (title.length() > 50) {
      throw new Exception("The title max length is 50 characters");
    }

    this.title = title;
  }

}
