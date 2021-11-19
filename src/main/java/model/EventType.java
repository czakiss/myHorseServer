package model;

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
@Table(name = "eventType")

public class EventType {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="eventTypeId", nullable = false, unique=true)
    private Integer eventTypeId;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="description", nullable = false)
    private String description;

    @Column(name="pointsScored", nullable = false)
    private Integer pointsScored;

}
