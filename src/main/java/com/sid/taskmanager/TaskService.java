package com.sid.taskmanager;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repo;

    // ✅ Constructor Injection (no warning)
    public TaskService(TaskRepository repo) {
        this.repo = repo;
    }

    public Task createTask(Task task) {
        if (task.getTitle() == null || task.getTitle().isEmpty()) {
            throw new RuntimeException("Title cannot be empty");
        }
        return repo.save(task);
    }

    public List<Task> getTasks() {
        return repo.findAll();
    }

    public List<Task> getTasksByStatus(String status) {
        return repo.findByStatus(status);
    }

    public void deleteTask(int id) {
        repo.deleteById(id);
    }

    public Task updateTask(int id, Task newTask) {
        Task task = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setTitle(newTask.getTitle());
        task.setStatus(newTask.getStatus());

        return repo.save(task);
    }
}