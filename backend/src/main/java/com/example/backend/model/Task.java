package com.example.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Table(name = "tasks")
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String title;


    @Column(columnDefinition = "TEXT")
    private String description;


    private Boolean completed = false;


    private Instant createdAt = Instant.now();


}
