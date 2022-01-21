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
@Table(name = "event_list")

public class EventList {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="event_list", nullable = false, unique=true)
    private Integer eventListId;

    @OneToOne
    private Horse horse;

    @ManyToOne
    private Event event;
}
