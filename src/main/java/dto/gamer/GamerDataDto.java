package dto.gamer;
import dto.role.Role;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class GamerDataDto {
    private String gamerId;
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
