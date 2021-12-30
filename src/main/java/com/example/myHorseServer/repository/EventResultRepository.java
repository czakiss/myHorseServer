package com.example.myHorseServer.repository;

import com.example.myHorseServer.dto.event.EventResult;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventResultRepository extends JpaRepository<EventResult, Integer> {

    @Cacheable
    Optional<EventResult> findEventResultById(Integer eventResultId);

    @Cacheable
    Optional<EventResult> findEventResultByName(String eventResultName);
}
