package com.example.backend.controller;

import com.example.backend.dto.TaskCreateDTO;
import com.example.backend.model.Task;
import com.example.backend.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service){
        this.service = service;
    }

    @GetMapping
    public List<Task> list(){
        return service.getRecentFive();
    }


    @PostMapping
    public Task create(@Valid  @RequestBody TaskCreateDTO t){
        return service.create(t);
    }


    @PostMapping("/{id}/done")
    public void done(@PathVariable Long id){
        service.markDone(id);
    }
}
