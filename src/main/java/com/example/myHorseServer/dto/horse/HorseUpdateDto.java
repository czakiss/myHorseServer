package com.example.myHorseServer.dto.horse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class HorseUpdateDto {
    private Integer id;
    private String name;
    private HorseBreed breed;
    private float fast;
    private float resilience;
    private float health;
    private float hungry;
    private float thirst;
    private String state;
}
