package com.example.todo.mapper;

import com.example.todo.entity.Event;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EventMapper {
    List<Event> getEvents();
    Event getEventById(long id);
}
