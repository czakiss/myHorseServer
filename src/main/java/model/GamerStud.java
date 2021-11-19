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
@Table(name = "gamer_stud")

public class GamerStud {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="gamerStudId", nullable = false, unique=true)
    private  Integer gamerStudId;

    @OneToOne(mappedBy = "gamerId")
    private Gamer gamerId;

    @Column(name="name", nullable = false)
    private String name;

}
