package br.com.hecherat.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.hecherat.todolist.errors.ResponseErrorModel;

@Service
public class UserService {
  // Indica para o Spring que ele deve gerenciar o ciclo de vida
  @Autowired
  private IUserRepository userRepository;

  public ResponseEntity createUser(@RequestBody UserModel userModel) {
    var userEmail = this.userRepository.findByEmail(userModel.getEmail());
    var userName = this.userRepository.findByUserName(userModel.getUserName());

    if (userEmail != null || userName != null) {
      ResponseErrorModel error = new ResponseErrorModel();
      error.setMessage("User already exists.");
      error.setStatus(400);

      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    try {
      var hashedPassword = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());
      userModel.setPassword(hashedPassword);

      var createdUser = this.userRepository.save(userModel);

      return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);

    } catch (Exception e) {

      ResponseErrorModel error = new ResponseErrorModel();
      error.setMessage("Something get wrong");
      error.setStatus(500);

      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
  }
}
