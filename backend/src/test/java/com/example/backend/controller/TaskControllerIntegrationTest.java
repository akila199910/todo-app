package com.example.backend.controller;

import com.example.backend.model.Task;
import com.example.backend.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import java.time.Instant;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc

class TaskControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepository repo;

    @BeforeEach
    void setup() {
        repo.deleteAll();

        Task t = new Task();
        t.setTitle("Test Task");
        t.setDescription("desc");
        t.setCompleted(false);
        t.setCreatedAt(Instant.now());
        repo.save(t);
    }

    // GET /api/tasks
    @Test
    void testListTasks() throws Exception {
        mockMvc.perform(get("/api/tasks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(lessThanOrEqualTo(5))));
    }

    // POST /api/tasks
    @Test
    void testCreateTask() throws Exception {
        String json = """
                {
                  "title": "New Task",
                  "description": "from test"
                }
                """;

        mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())         // your controller returns 200
                .andExpect(jsonPath("$.title", is("New Task")));
    }

    // POST /api/tasks/{id}/done
    @Test
    void testMarkDone() throws Exception {
        Task saved = repo.findAll().get(0);

        mockMvc.perform(post("/api/tasks/{id}/done", saved.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("Task updated successfully.")));
    }

    // When marking done with missing ID, expect 404
    @Test
    void testMarkDone_NotFound() throws Exception {
        mockMvc.perform(post("/api/tasks/{id}/done", 99999))
                .andExpect(status().isNotFound());
    }
}
