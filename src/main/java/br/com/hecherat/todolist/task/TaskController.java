// Controller = primeira camada de acesso ao executar um request '/tasks'
package br.com.hecherat.todolist.task;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

// SpringBoot annotation
@RestController
@RequestMapping("/tasks")
public class TaskController {
  // Indica para o Spring que ele deve gerenciar o ciclo de vida
  @Autowired
  private TaskService taskService;

  // SpringBoot annotation (Método e rota)
  @PostMapping("")
  public ResponseEntity<TaskModel> create(
      @RequestBody TaskModel taskModel,
      HttpServletRequest request) {
    return taskService.createTask(taskModel, request);
  }

  // SpringBoot annotation (Método e rota)
  @GetMapping("")
  public ResponseEntity<TaskModel[]> list(HttpServletRequest request) {
    return taskService.getTaskList(request);
  }

  // SpringBoot annotation (Método e rota)
  @PutMapping("/{id}")
  public ResponseEntity<TaskModel> update(
      @RequestBody TaskModel taskModel,
      HttpServletRequest request,
      @PathVariable UUID id) {
    return taskService.updateTask(taskModel, request, id);
  }
}
