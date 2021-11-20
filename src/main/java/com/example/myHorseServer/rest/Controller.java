package com.example.myHorseServer.rest;

import com.example.myHorseServer.service.GamerService;
import com.example.myHorseServer.dto.gamer.GamerLoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/")
public class Controller {

    @Autowired
    private GamerService gamerService;//referencja

    @PostMapping(value = "/login")
    public ResponseEntity<?> loginGamer(@RequestBody GamerLoginDto dto){
        System.out.println("logowanie");
        if(gamerService.login(dto.getEmail(), dto.getPassword())) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(401).build();
    }
}
