package com.example.myHorseServer.service;

import com.example.myHorseServer.dto.gamerStud.GamerStudDeleteResponse;
import com.example.myHorseServer.dto.gamerStud.GamerStudRegisterResponse;
import com.example.myHorseServer.exception.GamerStudNotFound;
import com.example.myHorseServer.model.GamerStud;
import com.example.myHorseServer.repository.GamerRepository;
import com.example.myHorseServer.repository.GamerStudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
public class GamerStudService {

    @Autowired
    private GamerStudRepository gamerStudRepository;
    @Autowired
    private GamerRepository gamerRepository;

    public GamerStudRegisterResponse gamerStudNew(GamerStud gamerStud){
        if(!gamerRepository.findByGamerEmail(gamerStud.getGamerId().getGamerEmail()).isPresent()){
            GamerStud gamerStudNew = new GamerStud();
            gamerStudNew.setGamerId(gamerStud.getGamerId());
            gamerStudNew.setGamerStudName(gamerStud.getGamerStudName());
            gamerStudNew = gamerStudRepository.save(gamerStudNew);
            return new GamerStudRegisterResponse(new GamerStud(
                    gamerStudNew.getGamerStudId(),
                    gamerStudNew.getGamerId(),
                    gamerStudNew.getGamerStudName()
            ),"New gamer stud created successfull");
        }return new GamerStudRegisterResponse(null, "Gamer not found");
    }

    public Iterable<GamerStud> findAll(){
        return gamerStudRepository.findAll();
    }

    public GamerStud loadGamerStud(Integer gamerId) throws GamerStudNotFound {
        return gamerStudRepository.findByGamerStudId(gamerId.intValue())
                .orElseThrow(() -> new GamerStudNotFound(format("Gamer Stud with gamer id - %s, not found", gamerId))
                );
    }

    public void editGamerStud(GamerStud gamerStudChange){
        GamerStud gamerStud = gamerStudRepository.findByGamerStudId(gamerStudChange.getGamerStudId())
                .orElseThrow(()->new GamerStudNotFound(format("Not found stud with id - %s",gamerStudChange.getGamerStudId())));
        if(!gamerStudChange.equals(gamerStud)){
            if(!gamerStudChange.getGamerId().equals(gamerStud.getGamerId()) || gamerStudChange!=null){
                gamerStud.setGamerId(gamerStudChange.getGamerId());
            }else throw new RuntimeException("Gamer was not found");
            if(!gamerStudChange.getGamerStudName().equals(gamerStud.getGamerStudName()) || gamerStudChange.getGamerStudName()!=null){
                gamerStud.setGamerStudName(gamerStudChange.getGamerStudName());
            }else throw new RuntimeException("Name not change");
        }else throw new RuntimeException("No changes");
        gamerStudRepository.save(gamerStud);
    }

    public GamerStudDeleteResponse deleteGamerStud(Integer gamerStudId){
        GamerStud gamerStudDelete = gamerStudRepository.findByGamerStudId(gamerStudId)
                .orElseThrow(()->new GamerStudNotFound(format("Not found stud with id - %s",gamerStudId)));
        gamerStudRepository.findByGamerStudId(gamerStudDelete.getGamerStudId());

        return new GamerStudDeleteResponse(new GamerStud(
                gamerStudDelete.getGamerStudId(),
                gamerStudDelete.getGamerId(),
                gamerStudDelete.getGamerStudName()
        ),"Deleted successfull");

    }

}
