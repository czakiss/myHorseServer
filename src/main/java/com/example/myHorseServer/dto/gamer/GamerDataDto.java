package com.example.myHorseServer.dto.gamer;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data

public class GamerDataDto {
    private Integer gamerId;
    private String nickname;
    private Integer points;
    private Date lastLogin;
    private Date lastLogout;
    private Integer spendTime;
    //private Role role;
    //TODO: ustawić role !!!
    private double loc_x;
    private double loc_y;
    private double loc_z;
    private String gamerEmail;
    private String password;

}
