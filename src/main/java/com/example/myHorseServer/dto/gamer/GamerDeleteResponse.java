package com.example.myHorseServer.dto.gamer;

//import dto.gamer.GamerDataDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import service.GamerService;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class GamerDeleteResponse {
    private GamerDataDto gamer;
    private String message;
    //private GamerService.Delete_Status deleteStatus;
}
