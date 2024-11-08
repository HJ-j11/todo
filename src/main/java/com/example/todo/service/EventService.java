package com.example.todo.service;

import com.example.todo.dto.EventDto;
import com.example.todo.entity.Event;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EventService {
    public List<EventDto> findAllEvent(Pageable pageable);

    public EventDto getEventById(Long id);

    public void createEvent(EventDto eventDto);

    public void updateEvent(Long id, EventDto eventDto);

    public void deleteEvent(Long id);
}
