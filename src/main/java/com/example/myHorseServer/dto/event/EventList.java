package com.example.myHorseServer.dto.event;

import com.example.myHorseServer.model.Horse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class EventList {
    private Integer eventListId;
    private List<Horse> horse;
    private Event event;

}
