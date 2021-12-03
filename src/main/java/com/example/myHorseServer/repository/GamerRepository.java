package com.example.myHorseServer.repository;

import com.example.myHorseServer.dto.gamer.GamerRegisterDto;
import com.example.myHorseServer.model.Gamer;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;


@Repository
public interface GamerRepository extends JpaRepository<Gamer,Integer> {

    @Cacheable
    Optional<Gamer> findByEmail(String email);

    Optional<Gamer> findByNickname(String nickname);

    @Transactional
    @Modifying
    @Query(
            value = "INSERT INTO gamer (nickname,email,password,points,spend_time) VALUES(:nickname, :email, :password,0,0)",
            nativeQuery = true
    ) void registerUser(
            @Param("nickname") String nickname, @Param("password") String password,
            @Param("email") String email
    );


}
