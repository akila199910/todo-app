package com.example.backend.service;

import com.example.backend.dto.TaskCreateDTO;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.model.Task;
import com.example.backend.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository repo;

    @InjectMocks
    private TaskService service;

    @Test
    void create_success() {
        TaskCreateDTO dto = new TaskCreateDTO();
        dto.setTitle("Hello");
        dto.setDescription("desc");

        Task saved = new Task();
        saved.setId(1L);
        saved.setTitle("Hello");
        saved.setDescription("desc");

        when(repo.save(any(Task.class))).thenReturn(saved);

        Task result = service.create(dto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Hello", result.getTitle());
    }


    // RECENT 5 TEST
    @Test
    void getRecentFive_success() {
        Task t1 = new Task(); t1.setId(1L); t1.setCreatedAt(Instant.now());
        Task t2 = new Task(); t2.setId(2L); t2.setCreatedAt(Instant.now());

        when(repo.findByCompletedFalseOrderByCreatedAtDesc(any()))
                .thenReturn(List.of(t1, t2));

        List<Task> result = service.getRecentFive();

        assertEquals(2, result.size());
        verify(repo).findByCompletedFalseOrderByCreatedAtDesc(any());
    }


    // MARK DONE SUCCESS
    @Test
    void markDone_success() {
        Task t = new Task();
        t.setId(1L);
        t.setCompleted(false);

        when(repo.findById(1L)).thenReturn(Optional.of(t));

        service.markDone(1L);

        assertTrue(t.getCompleted());
        verify(repo).save(t);
    }

    // MARK DONE NOT FOUND
    @Test
    void markDone_taskNotFound() {
        when(repo.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> service.markDone(1L));

        verify(repo, never()).save(any());
    }
}
