package com.example.myHorseServer.repository;


import com.example.myHorseServer.model.EventList;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface EventListRepository  extends JpaRepository<EventList, Integer> {

    @Cacheable
    Optional<EventList> findByEventListId(Integer eventListId);

    Iterable<EventList> findAllById(Integer eventListId);
}
