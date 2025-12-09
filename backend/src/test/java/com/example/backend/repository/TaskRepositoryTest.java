package com.example.backend.repository;

import com.example.backend.model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")

class TaskRepositoryTest {

    @Autowired
    private TaskRepository repo;

    @Test
    void saveAndFind() {
        Task t = new Task();
        t.setTitle("Hello");
        t.setDescription("Test");

        Task saved = repo.save(t);
        Optional<Task> found = repo.findById(saved.getId());

        assertThat(found).isPresent();
        assertThat(found.get().getTitle()).isEqualTo("Hello");
    }

    @Test
    void deleteTask() {
        Task t = new Task();
        t.setTitle("Delete me");

        Task saved = repo.save(t);
        repo.deleteById(saved.getId());

        assertThat(repo.findById(saved.getId())).isNotPresent();
    }
}
