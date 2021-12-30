package com.example.myHorseServer.dto.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class EventTypeDeleteResponse {
    private EventType eventType;
    private String message;
}
