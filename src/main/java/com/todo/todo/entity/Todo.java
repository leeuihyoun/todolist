package com.todo.todo.entity;



import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity(name = "todos")
@Data
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "member_idx")
    private int memberIdx;
    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "completed")
    private boolean completed;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


}
