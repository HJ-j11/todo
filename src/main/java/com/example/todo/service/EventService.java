package com.example.todo.service;

import com.example.todo.dto.EventDto;
import com.example.todo.entity.Event;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EventService {
    public List<EventDto> findAllEvent(Pageable pageable);

    public EventDto getEventById(int id);

    public void createEvent(EventDto eventDto);

    public void updateEvent(int id, EventDto eventDto);

    public void deleteEvent(int id);
}
