package main.service.interfaces;

import main.model.Task;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

public interface TaskService {

  ResponseEntity<ArrayList<Task>> getAllTask();

  ResponseEntity<Task> addTask(Task task);

  ResponseEntity<Task> getTask(int id);

  ResponseEntity<Task> putTask(int id, String name, String description);

  ResponseEntity<Task> patchTask(int id, String name, String description);

  ResponseEntity<?> deleteTask(int id);

  ResponseEntity<String> deleteAllTasks();
}
