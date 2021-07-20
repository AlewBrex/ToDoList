package main.controller;

import lombok.RequiredArgsConstructor;
import main.model.Task;
import main.service.interfaces.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class TaskListController {
  private final TaskService service;

  @DeleteMapping("")
  public ResponseEntity<String> delete() {
    return service.deleteAllTasks();
  }

  @RequestMapping("/")
  public String index(Model model) {
    ArrayList<Task> tasks = (ArrayList<Task>) service.getAllTask().getBody();
    model.addAttribute("tasks", tasks);
    assert tasks != null;
    model.addAttribute("tasksCount", tasks.size());
    return "index";
  }
}
