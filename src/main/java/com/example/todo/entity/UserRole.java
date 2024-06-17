package com.example.todo.entity;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN(1, "USER000"),
    USER(2, "USER001");

    private final int roleId;
    private final String value;

    UserRole(int roleId, String value) {
        this.roleId = roleId;
        this.value = value;
    }
}