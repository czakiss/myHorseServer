package com.example.myHorseServer.service;

import com.example.myHorseServer.dto.gamer.GamerRegisterDto;
import com.example.myHorseServer.exception.NotFoundException;
import com.example.myHorseServer.model.Gamer;
import com.example.myHorseServer.repository.GamerRepository;
import com.example.myHorseServer.dto.gamer.GamerDataDto;
import com.example.myHorseServer.security.JwtTokenUtil;
import com.example.myHorseServer.security.SecurityConfig;
import com.example.myHorseServer.status.RegisterStatus;
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return gamerRepository
                .findByNickname(username)
                .orElseThrow(() -> new NotFoundException()
                );
    }

    public RegisterStatus registerUser(GamerRegisterDto dto){
        if(gamerRepository.findByEmail(dto.getEmail()).isPresent()){
            return RegisterStatus.MAIL_EXISTS;
        }
        if(gamerRepository.findByNickname(dto.getNickname()).isPresent()){
            return RegisterStatus.NICKNAME_EXISTS;
        }
        gamerRepository.registerUser(
                dto.getNickname(),
                SecurityConfig.passwordEncoder().encode(dto.getPassword()),
                dto.getEmail()
        );
        if(gamerRepository.findByEmail(dto.getEmail()).isPresent() && gamerRepository.findByNickname(dto.getNickname()).isPresent()){
            return RegisterStatus.REGISTERED;
        }
        return RegisterStatus.UNDEFINED;
    }
}

