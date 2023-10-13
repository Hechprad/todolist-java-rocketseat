package br.com.hecherat.todolist.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<UserModel, UUID> {

  // Busca no banco pelo valor do e-mail
  UserModel findByEmail(String email);

  // Busca no banco pelo valor do userName
  UserModel findByUserName(String userName);
}
