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
@Table(name = "authme")

public class Authme {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="id", nullable = false, unique=true)
    private Integer id;

    @Column(name="username", nullable = false)
    private String username;

    @Column(name="password", nullable = false)
    private String password;

    @Column(name="ip", nullable = false)
    private String ip;

    @Column(name="lastlogin", nullable = false)
    private Date lastLogin;

    @Column(name="x", nullable = false)
    private double x;

    @Column(name="y", nullable = false)
    private double y;

    @Column(name="z", nullable = false)
    private double z;

    @Column(name="world", nullable = false)
    private String world;

    @Column(name="regdate", nullable = false)
    private Date regdate;

    @Column(name="regip", nullable = false)
    private String regip;

    @Column(name="yaw", nullable = true)
    private String yaw;

    @Column(name="pitch", nullable = true)
    private String pitch;

    @Column(name="email", nullable = true)
    private String email;

    @Column(name="isLogged", nullable = false)
    private boolean isLogged;

    @Column(name="hasSession", nullable = false)
    private boolean hasSession;

    @Column(name="totp", nullable = true)
    private String totp;

}
