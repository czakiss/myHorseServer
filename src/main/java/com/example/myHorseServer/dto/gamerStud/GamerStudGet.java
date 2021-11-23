package com.example.myHorseServer.dto.gamerStud;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class GamerStudGet {
    private Integer gamerId;
    private Integer horseId;
}