package com.example.myHorseServer.repository;

import com.example.myHorseServer.dto.gamerStud.GamerStud;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface GamerStudRepository extends JpaRepository<GamerStud, Integer> {

    @Cacheable
    Optional<GamerStud> findGamerStudById(Integer gamerStudId);

    @Cacheable
    Optional<GamerStud> findGamerStudByName(String gamerStudName);
}
