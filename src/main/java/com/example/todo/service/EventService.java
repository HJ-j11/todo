package com.example.todo.service;

import com.example.todo.dto.EventDto;
import com.example.todo.entity.Event;
import com.example.todo.mapper.EventMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventMapper eventMapper;
    private final ModelMapper modelMapper;

    public List<EventDto> getEvents() {
        List<Event> events = eventMapper.getEvents();
        return events.stream().map(event -> modelMapper.map(event, EventDto.class)).toList();
    }

    public EventDto getEventById(int id) {
        Event event = eventMapper.getEventById(id);
        return modelMapper.map(event, EventDto.class);
    }
}
