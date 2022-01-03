package com.example.myHorseServer.model;

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
@Table(name = "eventResult")

public class EventResult {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="eventResultId", nullable = false, unique=true)
    private Integer eventResultId;

    @ManyToOne
    private Horse horseId;

    @Column(name="pointsScored", nullable = false)
    private Integer pointsScored;


}
