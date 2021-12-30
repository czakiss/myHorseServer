package com.example.myHorseServer.repository;

import com.example.myHorseServer.dto.horse.Horse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface HorseRepository extends JpaRepository<Horse, Integer> {

    @Cacheable
    Optional<Horse> findHorseById(Integer horseId);

    @Cacheable
    Optional<Horse> findHorseByGamerId(Integer gamerId);
}
