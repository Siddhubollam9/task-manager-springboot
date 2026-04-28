package com.sid.taskmanager;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
public class TaskController {

    private final TaskService service;

    // ✅ Constructor Injection
    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping("/tasks")
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        return ResponseEntity.ok(service.createTask(task));
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getTasks() {
        return ResponseEntity.ok(service.getTasks());
    }

    @GetMapping("/tasks/status/{status}")
    public ResponseEntity<List<Task>> getTasksByStatus(@PathVariable String status) {
        return ResponseEntity.ok(service.getTasksByStatus(status));
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable int id, @RequestBody Task task) {
        return ResponseEntity.ok(service.updateTask(id, task));
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable int id) {
        service.deleteTask(id);
        return ResponseEntity.ok("Deleted");
    }
}