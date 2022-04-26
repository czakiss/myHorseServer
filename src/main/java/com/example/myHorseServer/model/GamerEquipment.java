package com.example.myHorseServer.model;


import lombok.*;
import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "gamer_equipment")
@ToString

public class GamerEquipment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="equipment_id", nullable = false, unique=true)
    private Integer equipmentId;

    @ManyToOne()
    @JoinColumn(name = "id_item")
    private Store idItem;


    @Column(name = "gamer_id")
    private Gamer gamerId;
}

