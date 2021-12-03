package com.example.myHorseServer.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;




@Table(name = "gamer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity


public class Gamer implements UserDetails, Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name="gamer_id", nullable = false, unique=true)
    private Integer gamerId;

    @Column(name="nickname", nullable = false)
    private String nickname;

    @Column(name="points", nullable = false)
    private Integer points = 0;

    @Column(name="last_login", nullable = true)
    private Date lastLogin;

    @Column(name="last_logout", nullable = true)
    private Date lastLogout;

    @Column(name="spend_time", nullable = false)
    private Integer spendTime = 0;

   // @OneToOne
    //@JoinColumn(name="role_id")
    //private Role role;

    @Column(name="loc_x", nullable = true)
    private Double loc_x;

    @Column(name="loc_y", nullable = true)
    private Double loc_y;

    @Column(name="loc_z", nullable = true)
    private Double loc_z;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="password", nullable = false)
    private String password;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return nickname;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
