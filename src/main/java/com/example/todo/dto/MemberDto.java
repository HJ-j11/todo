package com.example.todo.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MemberDto {
    private Long id;
    private String name;
    private String userRole;
    private LocalDateTime createdAt;
}
