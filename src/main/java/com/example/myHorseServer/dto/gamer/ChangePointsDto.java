package com.example.myHorseServer.dto.gamer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class ChangePointsDto {
    private String emial;
    private Integer points;
}
