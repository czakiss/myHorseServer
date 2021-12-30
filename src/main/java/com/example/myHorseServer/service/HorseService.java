package com.example.myHorseServer.service;

import com.example.myHorseServer.dto.horse.Breed;
import com.example.myHorseServer.dto.horse.BreedResponse;
import com.example.myHorseServer.dto.horse.Horse;
import com.example.myHorseServer.dto.horse.HorseResponse;
import com.example.myHorseServer.repository.BreedRepository;
import com.example.myHorseServer.repository.HorseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class HorseService {

    @Autowired
    private HorseRepository horseRepository;

    @Autowired
    private BreedRepository breedRepository;

    public HorseResponse createNewHorse(Horse horse){
        Horse creator = new Horse();
        creator.setAppearance(horse.getAppearance());
        creator.setBreed(horse.getBreed());
        creator.setFast(horse.getFast());
        creator.setGamerStud(horse.getGamerStud());
        creator.setHungry(horse.getHungry());
        creator.setName(horse.getName());
        creator.setThirst(horse.getThirst());
        creator.setValue(horse.getValue());
        creator = horseRepository.save(creator);

        return new HorseResponse(new Horse(
                creator.getHorseId(),
                creator.getGamerStud(),
                creator.getName(),
                creator.getBreed(),
                creator.getFast(),
                creator.getHungry(),
                creator.getThirst(),
                creator.getAppearance(),
                creator.getValue()
        ),"");
    }

    public BreedResponse createNewBreed(Breed breed){
        Breed creator = new Breed();
        creator.setHorseBreed(breed.getHorseBreed());
        creator.setFast(breed.getFast());
        creator.setHungry(breed.getHungry());
        creator.setThirst(breed.getThirst());
        creator.setAppearance(breed.getAppearance());
        creator.setValue(breed.getValue());
        creator = breedRepository.save(creator);

        return new BreedResponse(new Breed(
                creator.getBreedId(),
                creator.getHorseBreed(),
                creator.getFast(),
                creator.getHungry(),
                creator.getThirst(),
                creator.getAppearance(),
                creator.getValue()
        ),"");
    }

    /*public HorseResponse editNewHorse(Horse horseChange){


    }*/
}
