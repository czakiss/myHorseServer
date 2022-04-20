package com.example.myHorseServer.dto.gamer;
import com.example.myHorseServer.model.Role;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data

public class GamerDataDto {
    private Integer gamerId;
    private String authmeId;
    private String nickname;
    private Integer points;
    private Date lastLogin;
    private Date lastLogout;
    private Integer spendTime;
    private Role role;
    private double loc_x;
    private double loc_y;
    private double loc_z;
    private String email;
    private String password;

}
