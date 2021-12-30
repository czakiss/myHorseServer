package com.example.myHorseServer.repository;

import com.example.myHorseServer.dto.event.Event;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface EventRepository extends JpaRepository<Event, Integer> {

    @Cacheable
    Optional<Event> findEventById(Integer eventId);

    @Cacheable
    Optional<Event> findEventByName(String eventName);
}
