package com.example.myHorseServer.dto.gamer;

import lombok.*;

import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data

public class GamerLoginDto {
    private String email;
    private String password;

   /* @Override
    public boolean equalsLogin(Object o){
        if(o instanceof GamerLoginDto){
            GamerLoginDto other = (GamerLoginDto) o;
            return Objects.equals(email,other.email) && Objects.equals(password,other.password);
        }
        return false;
    }*/

}
