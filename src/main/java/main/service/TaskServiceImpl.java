package main.service;

import lombok.RequiredArgsConstructor;
import main.model.Task;
import main.model.repository.TaskRepository;
import main.service.interfaces.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

  private final TaskRepository taskRepository;

  @Override
  public ResponseEntity<ArrayList<Task>> getAllTask() {
    ArrayList<Task> tasks = new ArrayList<>();
    taskRepository.findAll().forEach(tasks::add);
    return new ResponseEntity<>(tasks, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Task> addTask(Task task) {
    Task tempTask = taskRepository.save(task);
    return new ResponseEntity<>(tempTask, HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity<Task> getTask(int id) {
    Optional<Task> optionalTask = taskRepository.findById(id);
    return optionalTask
        .map(task -> new ResponseEntity<>(task, HttpStatus.OK))
        .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
  }

  @Override
  public ResponseEntity<Task> putTask(int id, String name, String description) {
    Optional<Task> optionalTask = taskRepository.findById(id);
    if (optionalTask.isPresent()) {
      Task task = optionalTask.get();
      task.setDescription(description);
      task.setName(name);
      taskRepository.save(task);
      return new ResponseEntity<>(task, HttpStatus.OK);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
  }

  @Override
  public ResponseEntity<Task> patchTask(int id, String name, String description) {
    Optional<Task> optionalTask = taskRepository.findById(id);
    if (optionalTask.isPresent()) {
      Task task = optionalTask.get();
      if (!description.equals("")) {
        task.setDescription(description);
      }
      if (!name.equals("")) {
        task.setName(name);
      }
      taskRepository.save(task);
      return new ResponseEntity<>(task, HttpStatus.OK);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
  }

  @Override
  public ResponseEntity<?> deleteTask(int id) {
    if (taskRepository.existsById(id)) {
      taskRepository.deleteById(id);
      return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
  }

  @Override
  public ResponseEntity<String> deleteAllTasks() {
    if (taskRepository.count() > 0) {
      String allTasksCount = String.valueOf(taskRepository.count());
      try {
        taskRepository.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body(allTasksCount);
      } catch (Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
      }
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
  }
}
