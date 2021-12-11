package com.example.myHorseServer.service;

import com.example.myHorseServer.dto.gamer.*;
import com.example.myHorseServer.exception.NotFoundException;
import com.example.myHorseServer.model.Gamer;
import com.example.myHorseServer.repository.GamerRepository;
import com.example.myHorseServer.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import static java.lang.String.format;

@Service
public class GamerService implements UserDetailsService {

    @Autowired
    private GamerRepository gamerRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    public Iterable<Gamer> findAll(){
        return gamerRepository.findAll();
    }

    public void changePassword(ChangePasswordDto dto) {
        Gamer gamer = gamerRepository.getGamerByEmail(dto.getMail()).orElseThrow(()-> new NotFoundException());
        if(passwordEncoder.matches(dto.getOldPassword(), gamer.getPassword())) {
            gamer.setPassword(passwordEncoder.encode(dto.getNewPassword()));
            gamerRepository.save(gamer);
        } else
            throw new RuntimeException("Haslo zle");
    }

    public GamerDeleteResponse delete(String email) {
        Gamer deleted = gamerRepository.getGamerByEmail(email).orElseThrow(()-> new NotFoundException());
        gamerRepository.deleteById(deleted.getGamerId());

            return new GamerDeleteResponse(new GamerDataDto(
                        deleted.getGamerId(),
                        deleted.getNickname(),
                        deleted.getPoints(),
                        deleted.getLastLogin(),
                        deleted.getLastLogout(),
                        deleted.getSpendTime(),
                        deleted.getLoc_x(),
                        deleted.getLoc_y(),
                        deleted.getLoc_z(),
                        deleted.getEmail(),
                        deleted.getPassword()
                ), "Deleted successfull");
    }

    public GamerRegisterResponse register(GamerRegisterDto gamerRegisterDto){
        if(gamerRepository.getGamerByEmail(gamerRegisterDto.getEmail()).isEmpty()){
            Gamer gamer = new Gamer();
            gamer.setEmail(gamerRegisterDto.getEmail());
            gamer.setPassword(passwordEncoder.encode(gamerRegisterDto.getPassword()));
            gamer.setNickname(gamerRegisterDto.getNickname());
            gamer.setRole(roleRepository.findByRoleName("gracz").orElseThrow(() -> new NotFoundException()));
            gamer.setLastLogin(Date.valueOf(LocalDate.now()));
            gamer.setLastLogout(Date.valueOf(LocalDate.now()));
            gamer.setPoints(0);
            gamer.setSpendTime(0);
            gamer.setLoc_x(0);
            gamer.setLoc_y(0);
            gamer.setLoc_z(0);
            gamer = gamerRepository.save(gamer);
            return new GamerRegisterResponse(new GamerDataDto(
                    gamer.getGamerId(),
                    gamer.getNickname(),
                    0,
                    null,
                    null,
                    0,
                    // deleted.get().,
                    0,
                    0,
                    0,
                    gamer.getEmail(),
                    gamer.getPassword()
            ),"Registration SUCCESSFULL");
        }
        return new GamerRegisterResponse(null,"Email in use");
    }
}
