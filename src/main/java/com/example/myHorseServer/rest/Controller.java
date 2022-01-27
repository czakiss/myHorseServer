package com.example.myHorseServer.rest;

import com.example.myHorseServer.dto.LoginDto;
import com.example.myHorseServer.dto.gamer.*;
import com.example.myHorseServer.model.Gamer;
import com.example.myHorseServer.security.JwtTokenUtil;
import com.example.myHorseServer.service.GamerService;
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
    @GetMapping(value="/getuser")
        public ResponseEntity<?> usergetter(@AuthenticationPrincipal Gamer gamer){
            return new ResponseEntity<>(gamer, HttpStatus.OK) ;
        }

    @PostMapping(value = "/login")
    public ResponseEntity<?> loginGamer(@RequestBody GamerLoginDto dto){
        System.out.println("logowanie");

        try {
            Authentication authenticate = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    dto.getEmail(), dto.getPassword()
                            )
                    );

            Gamer gamer = (Gamer) authenticate.getPrincipal();

            String token = jwtTokenUtil.generateAccessToken(gamer);

            return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, token).body(new LoginDto(token));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    //change

    @PutMapping(value = "/gamer/changerole")
    public ResponseEntity<?> changeRole(@RequestBody ChangeGamerRole role){
        System.out.println("Change gamer role");
        gamerService.changeRole(role);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value="/gamer/changeinformation")
    public ResponseEntity<?> changeInformationGame(@RequestBody ChangeInformationGame information){
        System.out.println("Change game information  -> login, logout");
        gamerService.changeInformationGame(information);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value="/gamer/changepoints")
    public ResponseEntity<?> changePoints(@RequestBody ChangePointsDto points){
        System.out.println("hange game information -> points");
        gamerService.changePoints(points);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value="/gamer/changeposition")
    public ResponseEntity<?> changeGamerPosition(@RequestBody ChangeGamerPosition position){
        System.out.println("hange game information -> login, logout");
        gamerService.changeGamerPosition(position);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/gamer/changedata")
    public ResponseEntity<?> changeData(@RequestBody ChangeDataDto dto){
        System.out.println("Change gamer datas");
        gamerService.changeData(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/gamer/changepassword")
    public ResponseEntity<?> chanePassword(@RequestBody ChangePasswordDto dto){
        System.out.println("Change password");
        gamerService.changePassword(dto);
        return ResponseEntity.ok().build();
    }

    //delete user

    @DeleteMapping(value = "/deleteuser/{email}")
    public ResponseEntity<GamerDeleteResponse> deleteUser(@AuthenticationPrincipal Gamer gamer, @PathVariable String email) {
        if(gamer.getRole().getRoleName().equalsIgnoreCase("admin")) {
            return ResponseEntity.ok(gamerService.delete(email));
        }
        return ResponseEntity.badRequest().body(new GamerDeleteResponse(null, "Error"));
    }

    //register

    @CrossOrigin
    @PostMapping(value = "/register")
    public ResponseEntity<GamerRegisterResponse> registerRegister(@RequestBody GamerRegisterDto gamerRegisterDto) {
        GamerRegisterResponse registrationResponse = gamerService.register(gamerRegisterDto);

        System.out.println("--- User registration");

        if (registrationResponse.getMessage().equals("Registration SUCCESSFULL")) {
            return new ResponseEntity<>(registrationResponse, HttpStatus.OK);
        } else if (registrationResponse.getMessage().equals("REGISTRATION FAILED")) {
            return new ResponseEntity<>(registrationResponse, HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(registrationResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
