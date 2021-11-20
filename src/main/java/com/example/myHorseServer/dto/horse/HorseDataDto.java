package com.example.myHorseServer.dto.horse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class HorseDataDto {
    private Integer horseId;
    //private GamerStud gamerStud;
    private String name; // imie konia
    private Breed breed; // rasa konia
    private double fast; // szybkość konia
    private double hungry; // głód konia
    private Integer thirst; // pragnienie konia
    private double appearance;
    private double value;
}
