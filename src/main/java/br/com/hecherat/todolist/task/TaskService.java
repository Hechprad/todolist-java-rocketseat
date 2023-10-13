package br.com.hecherat.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.hecherat.todolist.errors.ResponseErrorModel;
import br.com.hecherat.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class TaskService {
  // Indica para o Spring que ele deve gerenciar o ciclo de vida
  @Autowired
  private ITaskRepository taskRepository;

  public ResponseEntity createTask(
      @RequestBody TaskModel taskModel,
      HttpServletRequest request) {
    try {
      var userId = request.getAttribute("userId");
      // Cast do tipo UUID
      taskModel.setUserId((UUID) userId);

      var currentDate = LocalDateTime.now();
      // Valida se a tarefa está sendo criada com uma data/hora posterior à
      // atual e se a data de finalização é posterior à data de início
      if (currentDate.isAfter(taskModel.getStartAt()) ||
          currentDate.isAfter(taskModel.getEndAt()) ||
          taskModel.getEndAt().isBefore(taskModel.getStartAt())) {
        ResponseErrorModel error = new ResponseErrorModel();
        String errorMessage = currentDate.isAfter(taskModel.getStartAt()) ? "The 'startAt' date must be after now"
            : "The 'endAt' date must be after 'startAt' date and current date";
        error.setMessage(errorMessage);
        error.setStatus(400);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
      }

      var createdTask = this.taskRepository.save(taskModel);

      return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);

    } catch (Exception e) {

      ResponseErrorModel error = new ResponseErrorModel();
      error.setMessage("Something get wronghehe");
      error.setStatus(500);

      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
  }

  public ResponseEntity getTaskList(HttpServletRequest request) {
    try {
      var userId = request.getAttribute("userId");
      var taskList = this.taskRepository.findByUserId((UUID) userId);

      return ResponseEntity.status(HttpStatus.OK).body(taskList);

    } catch (Exception e) {

      ResponseErrorModel error = new ResponseErrorModel();
      error.setMessage("Something get wrong");
      error.setStatus(500);

      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
  }

  public ResponseEntity updateTask(
      @RequestBody TaskModel taskModel,
      HttpServletRequest request,
      @PathVariable UUID id) {
    try {
      var userId = request.getAttribute("userId");
      var task = this.taskRepository.findById(id).orElse(null);

      if (task == null) {
        ResponseErrorModel error = new ResponseErrorModel();
        error.setMessage("This task doesn't exists");
        error.setStatus(404);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
      }

      if (!userId.equals(task.getUserId())) {
        ResponseErrorModel error = new ResponseErrorModel();
        error.setMessage("That is not your task");
        error.setStatus(400);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
      }

      Utils.copyNonNullProperties(taskModel, task);

      var updatedTask = this.taskRepository.save(task);

      return ResponseEntity.status(HttpStatus.OK).body(updatedTask);

    } catch (Exception e) {

      ResponseErrorModel error = new ResponseErrorModel();
      error.setMessage("Something get wrong");
      error.setStatus(500);

      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
  }
}
