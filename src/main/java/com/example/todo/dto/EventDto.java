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
    private String startDate;
    private String endDate;
    private String imageUrl;
    private String location;
    private LocalDateTime createdAt;
}
