package com.example.backend.service;

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

    public Task create(Task t) {
        t.setId(null);
        return repo.save(t);
    }


    public List<Task> getRecentFive() {
        return repo.findByCompletedFalseOrderByCreatedAtDesc(PageRequest.of(0,5));
    }


    public Optional<Task> markDone(Long id) {
        return repo.findById(id).map(t -> {
            t.setCompleted(true);
            return repo.save(t);
        });
    }
}
