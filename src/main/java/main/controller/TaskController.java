package main.controller;

import lombok.RequiredArgsConstructor;
import main.model.Task;
import main.service.interfaces.TaskService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "tasks/")
@ComponentScan("service")
@RequiredArgsConstructor
public class TaskController {

  private final TaskService taskService;

  @GetMapping(value = "")
  public ResponseEntity<ArrayList<Task>> list() {
    return taskService.getAllTask();
  }

  @PostMapping(value = "")
  public ResponseEntity<Task> add(Task task) {
    return taskService.addTask(task);
  }

  @GetMapping("{id}")
  public ResponseEntity<Task> get(@PathVariable int id) {
    return taskService.getTask(id);
  }

  @PutMapping(
      value = "{id}",
      params = {"name", "description"})
  public @ResponseBody ResponseEntity<Task> put(
      @PathVariable int id,
      @RequestParam(value = "name") String name,
      @RequestParam(value = "description") String description) {
    return taskService.putTask(id, name, description);
  }

  @PatchMapping(
      value = "{id}",
      params = {"name", "description"})
  public @ResponseBody ResponseEntity<Task> patch(
      @PathVariable int id,
      @RequestParam(value = "name") String name,
      @RequestParam(value = "description") String description) {
    return taskService.patchTask(id, name, description);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<?> delete(@PathVariable int id) {
    return taskService.deleteTask(id);
  }
}
