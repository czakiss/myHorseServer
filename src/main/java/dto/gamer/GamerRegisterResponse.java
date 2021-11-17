package dto.gamer;

//import dto.gamer.GamerDataDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import service.GamerService;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class GamerRegisterResponse {
    //private GamerDataDto gamer;
    private String message;
    //private GamerService.Register_Response registerResponse;
}
