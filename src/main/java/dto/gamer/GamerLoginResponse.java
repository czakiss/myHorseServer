package dto.gamer;

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

public class GamerLoginResponse {
    //private GamerDataDto gamer;
    private String message;
   // private GamerService.Login_Status gamerLoginStatus;
}
