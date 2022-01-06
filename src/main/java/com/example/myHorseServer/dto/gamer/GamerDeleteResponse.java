package com.example.myHorseServer.dto.gamer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class GamerDeleteResponse {
    private GamerDataDto gamer;
    private String message;
}
