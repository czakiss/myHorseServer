package dto.event;

import dto.horse.HorseDataDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class EventType {
    private Integer id;
    private String name;
    private String description;
    private Integer pointsScored;
}
