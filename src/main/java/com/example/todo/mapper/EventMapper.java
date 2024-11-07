package com.example.todo.mapper;

import com.example.todo.dto.EventDto;
import com.example.todo.entity.Event;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface EventMapper {
    List<Event> findAllEvents();
    Optional<Event> findEventById(int id);
    void createEvent(Event event);
    void updateEvent(@Param("id") int id, @Param("post") EventDto eventDto);
    void deleteEvent(int id);
}
