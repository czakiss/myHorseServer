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
@Table(name = "gamer_stud")

public class GamerStud {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="gamer_stud_id", nullable = false, unique=true)
    private  Integer gamerStudId;

    @OneToOne
    @JoinColumn(name = "gamer_id")
    private Gamer gamerId;

    @Column(name="name", nullable = false)
    private String gamerStudName;

}
