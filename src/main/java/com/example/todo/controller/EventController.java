package com.example.todo.controller;


import com.example.todo.dto.EventDto;
import com.example.todo.dto.api.ApiResponse;
import com.example.todo.entity.error.CommonErrorCode;
import com.example.todo.entity.exception.RestApiException;
import com.example.todo.service.EventServiceImpl;
import io.swagger.v3.oas.annotations.Operation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class EventController {

    private final EventServiceImpl eventService;

    @Operation( summary = "Event List 조회", description = "Event 전체를 조회한다.")
    @GetMapping("/events")
    public ApiResponse<List<EventDto>> findAllEvents(@PageableDefault(size=5) Pageable pageable) {
        List<EventDto> Events = eventService.findAllEvent(pageable);
        return ApiResponse.success(Events);
    }

    @Operation( summary = "Event 단건 조회")
    @GetMapping("/events/{id}")
    public ApiResponse<EventDto> findEventById(@PathVariable Long id) {
        EventDto event = eventService.getEventById(id);
        if(event == null)
            throw new RestApiException(CommonErrorCode.RESOURCE_NOT_FOUND);
        return ApiResponse.success(event);
    }

    @Operation( summary = "Event 생성")
    @PostMapping("/events")
    public ApiResponse<Void> saveEvent(@RequestBody EventDto eventDto) {
        eventService.createEvent(eventDto);
        return ApiResponse.successCreated();
    }

    @Operation( summary = "Event 수정")
    @PutMapping("/events/{id}")
    public ApiResponse<Void> updateEvent(@PathVariable Long id, @RequestBody EventDto eventDto) {
        eventService.updateEvent(id, eventDto);
        return ApiResponse.successWithoutContent();
    }

    @Operation( summary = "Event 삭제")
    @DeleteMapping("/events/{id}")
    public ApiResponse<Void> deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
        return ApiResponse.successWithoutContent();
    }

}
