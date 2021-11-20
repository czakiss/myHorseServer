package com.example.myHorseServer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "gamer")

public class Gamer {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="gamer_id", nullable = false, unique=true)
    private Integer gamerId;

    @Column(name="nickname", nullable = false)
    private String nickname;

    @Column(name="points", nullable = false)
    private Integer points;

    @Column(name="last_login", nullable = false)
    private Date lastLogin;

    @Column(name="last_logout", nullable = false)
    private Date lastLogout;

    @Column(name="spend_time", nullable = false)
    private Integer spendTime;

    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;

    @Column(name="loc_x", nullable = false)
    private double loc_x;

    @Column(name="loc_y", nullable = false)
    private double loc_y;

    @Column(name="loc_z", nullable = false)
    private double loc_z;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="password", nullable = false)
    private String password;
}
