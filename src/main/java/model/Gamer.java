package model;

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
@Table(name = "gamer")

public class Gamer {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="gamerId", nullable = false, unique=true)
    private String gamerId;

    @Column(name="nickname", nullable = false)
    private String nickname;

    @Column(name="points", nullable = false)
    private Integer points;

    @Column(name="lastLogin", nullable = false)
    private Date lastLogin;

    @Column(name="lastLogout", nullable = false)
    private Date lastLogout;

    @Column(name="spendTime", nullable = false)
    private Integer spendTime;

    @Column(name="role", nullable = false)
    private Role role;

    @Column(name="loc_x", nullable = false)
    private double loc_x;

    @Column(name="loc_y", nullable = false)
    private double loc_y;

    @Column(name="loc_z", nullable = false)
    private double loc_z;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="password", nullable = false)
    private String password;
}
