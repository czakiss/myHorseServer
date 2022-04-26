package com.example.myHorseServer.dto;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data

public class AuthmeDto {
    private Integer id;
    private String username;
    private String password;
    private String ip;
    private Date lastLogin;
    private double x;
    private double y;
    private double z;
    private String world;
    private Date regdate;
    private String regip;
    private String yaw;
    private String pitch;
    private String email;
    private boolean isLogged;
    private boolean hasSession;
    private String totp;
}
