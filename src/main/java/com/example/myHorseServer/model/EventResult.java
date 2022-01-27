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
@Table(name = "event_result")

public class EventResult {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="event_result_id", nullable = false, unique=true)
    private Integer eventResultId;

    @OneToOne
    @JoinColumn(name = "event")
    private Event eventId;

    @ManyToOne
    private Horse horseId;

    @Column(name="points_scored", nullable = false)
    private Integer pointsScored;

}
