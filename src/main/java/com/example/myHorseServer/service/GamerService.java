package com.example.myHorseServer.service;

import com.example.myHorseServer.exception.NotFoundException;
import com.example.myHorseServer.repository.GamerRepository;
import com.example.myHorseServer.dto.gamer.GamerDataDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GamerService {

    @Autowired
    private GamerRepository gamerRepository;

    public boolean login(String email, String password) {
        GamerDataDto gto = gamerRepository.findByEmail(email).orElseThrow(() -> new NotFoundException());
        return gto.getEmail().equalsIgnoreCase(email) && gto.getPassword().equals(password);
    }

}
