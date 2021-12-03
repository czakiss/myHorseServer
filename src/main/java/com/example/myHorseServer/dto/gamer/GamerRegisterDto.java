package com.example.myHorseServer.dto.gamer;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GamerRegisterDto {
    private String email;
    private String nickname;
    private String password;
}
