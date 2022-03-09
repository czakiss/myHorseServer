package com.example.myHorseServer.model;


import com.example.myHorseServer.dto.horse.HorseBreed;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "breed")

public class Breed {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="breed_id", nullable = false, unique=true)
    private Integer breedId;

    @Column(name="horse_breed", nullable = false)
    private HorseBreed horseBreed;

    @Column(name="breed_fast", nullable = false)
    private double fast;

    @Column(name="breed_hungry", nullable = false)
    private double hungry;

    @Column(name="breed_thirst", nullable = false)
    private double thirst;

    @Column(name="breed_appearance", nullable = false)
    private double appearance;

    @Column(name="breed_value", nullable = false)
    private double value;
}
