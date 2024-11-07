package com.example.todo.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {
    private int id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
}
