package com.example.myHorseServer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "horse")

public class Horse {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="horse_id", nullable = false, unique=true)
    private Integer horseId;

    @ManyToOne
    private GamerStud gamerStud;

    @Column(name="name", nullable = false)
    private String name; // imie konia

    @OneToOne
    @JoinColumn(name = "breedId")
    private Breed breed; // rasa konia

    @Column(name="fast", nullable = false)
    private double fast; // szybkość konia

    @Column(name="hungry", nullable = false)
    private double hungry; // głód konia

    @Column(name="thirst", nullable = false)
    private double thirst; // pragnienie konia

    @Column(name="appearance", nullable = false)
    private double appearance;

    @Column(name="value", nullable = false)
    private double value;
    
}
