package br.com.hecherat.todolist.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// Usado para definir classes globais para tratamento de exceções 
@ControllerAdvice
public class ExceptionHandlerController {

  // Toda exception do tipo HttpMessageNotReadableException lançada será
  // interceptada aqui
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ResponseErrorModel> handleHttpMEssageNotReadableException(
      HttpMessageNotReadableException e) {
    ResponseErrorModel error = new ResponseErrorModel();
    error.setMessage(e.getMostSpecificCause().getMessage());
    error.setStatus(400);

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
  }
}
