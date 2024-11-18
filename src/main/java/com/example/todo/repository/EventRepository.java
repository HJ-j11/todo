package com.example.todo.repository;

import com.example.todo.dto.EventDto;
import com.example.todo.entity.Event;
import jakarta.transaction.Transactional;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Event e SET e.title = :#{#dto.title} WHERE e.id = :id")
    int updateEvent(@Param("id") Long id, @Param("dto") EventDto dto);
}
