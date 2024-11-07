package com.example.todo.entity;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    private Long id;
    private String title;
    private String startDate;
    private String endDate;
    private String location;
    private LocalDateTime createdAt;
}
