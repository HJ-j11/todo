package com.example.todo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Event {
    @Id
    private Long id;
    private String title;
    private String startDate;
    private String endDate;
    private String location;
    private String imageUrl;
    private LocalDateTime createdAt;
}
