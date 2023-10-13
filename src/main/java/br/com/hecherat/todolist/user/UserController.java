// Controller = primeira camada de acesso ao executar um request '/users'
package br.com.hecherat.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// SpringBoot annotation
@RestController
@RequestMapping("/users")
public class UserController {

  // Indica para o Spring que ele deve gerenciar o ciclo de vida
  @Autowired
  private UserService userService;
  // private IUserRepository userRepository;

  // SpringBoot annotation (Método e rota)
  @PostMapping("")
  public ResponseEntity create(@RequestBody UserModel userModel) {
    return userService.createUser(userModel);
  }
}
