package br.com.hecherat.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

// Cria os getters e setters
@Data
// Mapeia a tabela no DB
@Entity(name = "tb_users")
public class UserModel {

  // Primary key
  @Id
  // Responsável por gerar automaticamente o id no DB
  @GeneratedValue(generator = "UUID")
  private UUID id;

  // Adiciona automaticamente a data de criação no DB
  @CreationTimestamp
  private LocalDateTime createdAt;

  // Indica que não devem existir valores iguais para esta coluna
  @Column(unique = true)
  private String email;
  private String name;
  private String password;
  // Indica que não devem existir valores iguais para esta coluna
  @Column(unique = true)
  private String userName;

  public String toString() {
    return "Name: " + getName() + ", " + "UserName: " + getUserName();
  }
}
