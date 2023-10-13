package br.com.hecherat.todolist.errors;

import lombok.Data;

// Cria os getters e setters
@Data
public class ResponseErrorModel {
  private String message;
  private Integer status;
}
