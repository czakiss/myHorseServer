package dto.gamerStud;


import dto.gamer.GamerDataDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class GamerStudDataDto {
    private  Integer gamerStudId;
    private GamerDataDto gamerId;
    private String name;
}
