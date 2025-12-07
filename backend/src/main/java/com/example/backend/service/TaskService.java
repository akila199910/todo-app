package com.example.backend.service;

import com.example.backend.dto.TaskCreateDTO;
import com.example.backend.dto.TaskUpdateResDTO;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.model.Task;
import com.example.backend.repository.TaskRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository repo;

    public TaskService(TaskRepository repo) {
        this.repo = repo;
    }

    public Task create(TaskCreateDTO t) {

        Task task = new Task();
        task.setTitle(t.getTitle());
        task.setDescription(t.getDescription());
        return repo.save(task);
    }


    public List<Task> getRecentFive() {
        return repo.findByCompletedFalseOrderByCreatedAtDesc(PageRequest.of(0,5));
    }

    public TaskUpdateResDTO markDone(Long id) {

        Task task = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        task.setCompleted(true);
        repo.save(task);

        TaskUpdateResDTO taskUpdateResDTO = new TaskUpdateResDTO();
        taskUpdateResDTO.setMessage("Task updated successfully.");

        return taskUpdateResDTO;
    }

}
