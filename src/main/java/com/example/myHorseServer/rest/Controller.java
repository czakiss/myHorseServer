package com.example.myHorseServer.rest;

import com.example.myHorseServer.dto.LoginDto;
import com.example.myHorseServer.model.Gamer;
import com.example.myHorseServer.security.JwtTokenUtil;
import com.example.myHorseServer.service.GamerService;
import com.example.myHorseServer.dto.gamer.GamerLoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
//@RequestMapping("/api")
@RequiredArgsConstructor
public class Controller {

    private final AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private GamerService gamerService;//referencja

    @GetMapping(value = "/username")
    public ResponseEntity<?> username(@AuthenticationPrincipal Gamer gamer) {
        return ResponseEntity.ok().body(new LoginDto(gamer.getUsername()));
    }


}
