package com.example.todo.entity;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
}
