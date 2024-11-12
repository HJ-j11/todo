package com.example.todo.service;

import com.example.todo.dto.EventDto;
import com.example.todo.entity.Event;
import com.example.todo.mapper.EventMapper;
import com.example.todo.repository.EventRepository;
import com.example.todo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;

    public Page<EventDto> findAllEvent(Pageable pageable) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), 10);
        return eventRepository.findAll(pageRequest).map(event -> modelMapper.map(event, EventDto.class));
    }

    public EventDto getEventById(Long id) {
        Optional<Event> post = eventRepository.findById(id);
        if(post.isEmpty())
            return null;
        return modelMapper.map(post, EventDto.class);
    }

    public void createEvent(EventDto eventDto) {
        eventRepository.save(modelMapper.map(eventDto, Event.class));
    }

    public void updateEvent(Long id, EventDto eventDto) {
        eventRepository.updateEvent(id, eventDto);
    }

    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}
