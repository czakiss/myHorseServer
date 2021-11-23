package com.example.myHorseServer.service;

import com.example.myHorseServer.exception.NotFoundException;
import com.example.myHorseServer.model.Gamer;
import com.example.myHorseServer.repository.GamerRepository;
import com.example.myHorseServer.dto.gamer.GamerDataDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
public class GamerService implements UserDetailsService {

    @Autowired
    private GamerRepository gamerRepository;

    public boolean login(String email, String password) {
        Gamer gto = gamerRepository.findByEmail(email).orElseThrow(() -> new NotFoundException());
        return gto.getEmail().equalsIgnoreCase(email) && gto.getPassword().equals(password);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return gamerRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(format("Gamer with emial - %s, not found", email))
                );
    }
}
