package com.example.todo.service;

import com.example.todo.dto.EventDto;
import com.example.todo.entity.Event;
import com.example.todo.mapper.EventMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventMapper eventMapper;
    private final ModelMapper modelMapper;

    public List<EventDto> findAllEvent(Pageable pageable) {
        List<Event> events = eventMapper.findAllEvents();
        return events.stream().map(event -> modelMapper.map(event, EventDto.class)).toList();
    }

    public EventDto getEventById(int id) {
        Optional<Event> post = eventMapper.findEventById(id);
        if(post.isEmpty())
            return null;
        return modelMapper.map(post, EventDto.class);
    }

    public void createEvent(EventDto eventDto) {
        eventMapper.createEvent(modelMapper.map(eventDto, Event.class));
    }

    public void updateEvent(int id, EventDto eventDto) {
        eventMapper.updateEvent(id, eventDto);
    }

    public void deleteEvent(int id) {
        eventMapper.deleteEvent(id);
    }
}
