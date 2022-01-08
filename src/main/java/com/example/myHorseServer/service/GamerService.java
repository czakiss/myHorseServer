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
        Gamer gto = gamerRepository.findByGamerEmail(email).orElseThrow(() -> new NotFoundException());
        return gto.getGamerEmail().equalsIgnoreCase(email) && gto.getPassword().equals(password);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return gamerRepository
                .findByGamerEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(format("Gamer with emial - %s, not found", email))
                );
    }

    public Iterable<Gamer> findAll(){
        return gamerRepository.findAll();
    }

    public void changePassword(ChangePasswordDto dto) {
        Gamer gamer = gamerRepository.findByGamerEmail(dto.getEmail()).orElseThrow(()-> new NotFoundException());
        if(passwordEncoder.matches(dto.getOldPassword(), gamer.getPassword())) {
            gamer.setPassword(passwordEncoder.encode(dto.getNewPassword()));
            gamerRepository.save(gamer);
        } else
            throw new RuntimeException("Incorrect password");
    }

    //TODO: sprawdzić changeRole, changeInformationGame, changeData
    public void changeRole(ChangeGamerRole role){
        Gamer gamer = gamerRepository.findByGamerEmail(role.getEmail()).orElseThrow(()-> new NotFoundException());
        Gamer gamerToChange = gamerRepository.findByGamerEmail(role.getEmailGamerToChange()).orElseThrow(()-> new NotFoundException());//sprawdzenie czy nasz gracz któremu chcemy zmienić role istnieje w bazie
        role.setAdminRole(gamer.getRole());
        if(role.getAdminRole().equals("0")){
            if(gamerToChange.getRole().equals(role.getNewRole())){
                throw new RuntimeException("The player has already been given such a role");
            }else {
                gamerToChange.setRole(role.getNewRole());
                gamerRepository.save(gamerToChange);
                System.out.println("Admin powers granted");
            }
        }else throw new RuntimeException("You are not authorized to change the role");
    }

    public void changeInformationGame(ChangeInformationGame informations){
        Gamer gamer = gamerRepository.findByGamerEmail(informations.getEmial()).orElseThrow(()-> new NotFoundException());
        if (!(informations.getLastLogin()).after(informations.getNewlastLogin()) ||
                !(informations.getLastLogout()).after(informations.getNewlastLogout())){
            gamer.setLastLogin(informations.getNewlastLogin());
            gamer.setLastLogout(informations.getNewlastLogout());
            informations.setNewspendTime((int) (informations.getNewlastLogout().getTime() - informations.getNewlastLogin().getTime()));
            //TODO: sprawdzic poprawnosc zapisu daty i jej wyliczenia spedzonego czasu.
            gamer.setSpendTime(informations.getSpendTime()+ informations.getNewspendTime());
            gamerRepository.save(gamer);
            System.out.println("Time was saved");
        }else throw new RuntimeException("Login time unchanged");
    }

    public void changePoints(ChangePointsDto points){
        Gamer gamer = gamerRepository.findByGamerEmail(points.getEmial()).orElseThrow(()-> new NotFoundException());
        if(!points.getNewPoints().equals(points.getPoints())){
            gamer.setPoints(gamer.getPoints()+points.getNewPoints());
            gamerRepository.save(gamer);
        }else throw new RuntimeException("Points unchanged");
    }

    public void changeGamerPosition(ChangeGamerPosition position){
        Gamer gamer = gamerRepository.findByGamerEmail(position.getEmail()).orElseThrow(()-> new NotFoundException());
        if(position.getLastLogout().after(gamer.getLastLogout())){
            gamer.setLoc_x(position.getLoc_x());
            gamer.setLoc_y(position.getLoc_y());
            gamer.setLoc_z(position.getLoc_z());
            gamerRepository.save(gamer);
            System.out.println("A new location has been saved");
        } else throw new RuntimeException("The last login time was not saved correctly");
    }

    public void changeData(ChangeDataDto dto){
        Gamer gamer = gamerRepository.findByGamerEmail(dto.getGamerEmail()).orElseThrow(()-> new NotFoundException());
        if(dto.getNewemail().isEmpty() && dto.getNewnickname().isEmpty()){
                System.out.println("No changes");
                throw new RuntimeException("No changes to email or nickname");
        }else {
            if(!dto.getNewemail().isEmpty()){
            gamer.setGamerEmail(dto.getNewemail());
            gamerRepository.save(gamer);
                System.out.println("Change email");
        }else if(!dto.getNewnickname().isEmpty()){
                gamer.setNickname(dto.getNewnickname());
                gamerRepository.save(gamer);
                System.out.println("Change nickname");
            }else{
                gamer.setGamerEmail(dto.getNewemail());
                gamer.setNickname(dto.getNewnickname());
                gamerRepository.save(gamer);
                System.out.println("Change email and nickname");
            }
        }
    }

    public GamerDeleteResponse delete(String email) {
        Gamer deleted = gamerRepository.findByGamerEmail(email).orElseThrow(()-> new NotFoundException());
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
                        deleted.getGamerEmail(),
                        deleted.getPassword()
                ), "Deleted successfull");
    }

    public GamerRegisterResponse register(GamerRegisterDto gamerRegisterDto){
        if(gamerRepository.findByGamerEmail(gamerRegisterDto.getEmail()).isEmpty()){
            Gamer gamer = new Gamer();
            gamer.setGamerEmail(gamerRegisterDto.getEmail());
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
                    //0,
                    0,
                    0,
                    0,
                    gamer.getGamerEmail(),
                    gamer.getPassword()
            ),"Registration SUCCESSFULL");
        }
        return new GamerRegisterResponse(null,"Email in use");
    }
}
