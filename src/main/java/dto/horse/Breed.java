package dto.horse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Breed {
    private Integer breedId;
    private HorseBreed horseBreed;
    private double fast;
    private Integer hungry;
    private Integer thirst;
    private double appearance;
    private double value;
}
