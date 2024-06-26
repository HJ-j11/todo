package com.example.todo.controller;

import com.example.todo.dto.EventDto;
import com.example.todo.dto.api.ApiResponse;
import com.example.todo.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping("/events")
    public ApiResponse<List<EventDto>> getEvents() {
        List<EventDto> events = eventService.getEvents();
        return ApiResponse.success(events);
    }

}
