package com.example.myHorseServer.service;

import com.example.myHorseServer.dto.event.*;
import com.example.myHorseServer.exception.EventNotFoundException;
import com.example.myHorseServer.repository.EventRepository;
import com.example.myHorseServer.repository.EventResultRepository;
import com.example.myHorseServer.repository.EventTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventTypeRepository eventTypeRepository;

    @Autowired
    private EventResultRepository eventResultRepository;

    public EventCreateResponse createEvent(Event event){
        Event creator = new Event();
        creator.setEventType(event.getEventType());
        creator.setDate(event.getDate());
        creator = eventRepository.save(creator);

        return new EventCreateResponse(new Event(
                creator.getEventId(),
                creator.getEventType(),
                creator.getDate()
        ),"Create new event - successfull");
    }

    public EventTypeCreateResponse createEventType(EventType eventType){
        EventType creator = new EventType();
        creator.setName(eventType.getName());
        creator.setDescription(eventType.getDescription());
        creator.setPointsScored(eventType.getPointsScored());
        creator = eventTypeRepository.save(creator);

        return new EventTypeCreateResponse(new EventType(
               creator.getEventTypeId(),
                creator.getName(),
                creator.getDescription(),
                creator.getPointsScored()
        ),"Create new event type - successfull");
    }

    public EventResultCreateResponse createEventResult(EventResult eventResult){
        EventResult creator = new EventResult();
        creator.setHorseId(eventResult.getHorseId());
        creator.setPointsScored(eventResult.getPointsScored());
        creator = eventResultRepository.save(creator);

        return new EventResultCreateResponse(new EventResult(
                creator.getId(),
                creator.getHorseId(),
                creator.getPointsScored()
        ),"Create new event - successfull");
    }

    public Iterable<Event> findAllEvent(){
        return eventRepository.findAll();
    }

    public Iterable<EventType> findAllEventType(){
        return eventTypeRepository.findAll();
    }

    public Iterable<EventResult> eventResultRepository(){
        return eventResultRepository.findAll();
    }

    public Event loadEventById(Integer eventId){
        return eventRepository.findEventById(eventId).orElseThrow(() -> new EventNotFoundException(format("Event with id - %s, not found", eventId))
        );
    }

    public Event loadEventByName(String eventName){
        return eventRepository.findEventByName(eventName).orElseThrow(()-> new EventNotFoundException(format("Event with name - s%, not found",eventName)));
    }

    public EventType loadEventTypeById(Integer eventTypeId){
        return eventTypeRepository.findEventTypeById(eventTypeId).orElseThrow(()-> new EventNotFoundException(format("Event type - %s, not found",eventTypeId)));
    }

    public EventType loadEventTypeByName(String eventTypeName){
        return eventTypeRepository.findEventTypeByName(eventTypeName).orElseThrow(()-> new EventNotFoundException(format("Event type - %s, not found",eventTypeName)));
    }

    public EventResult loadEventResultById(Integer eventResultId){
        return eventResultRepository.findEventResultById(eventResultId).orElseThrow(()-> new EventNotFoundException(format("Event result - %s, not found",eventResultId)));
    }

    public EventResult loadEventResultByName(String eventResultName){
        return eventResultRepository.findEventResultByName(eventResultName).orElseThrow(()-> new EventNotFoundException(format("Event result - %s, not found",eventResultName)));
    }

    public void changeEvent(Event eventChange){
        Event event = eventRepository.findEventById(eventChange.getEventId()).orElseThrow(()-> new EventNotFoundException(format("Event result - %s, not found",eventChange.getEventType())));
       if(eventChange.getEventType()!=null || event.getDate()!=null){
           if (eventChange.getEventType()!=null){
               event.setEventType(eventChange.getEventType());
           }if(event.getDate()!=null){
               event.setDate(eventChange.getDate());
           }
           eventRepository.save(event);
       }else throw new RuntimeException("Brak zmian");
    }

    public void chanegeEventResult(EventResult eventResultChange){
        EventResult eventResult = eventResultRepository.findEventResultById(eventResultChange.getId()).orElseThrow(()-> new EventNotFoundException(format("Event result - %s, not found",eventResultChange.getId())));

        if (eventResultChange.getHorseId()!=null || eventResultChange.getHorseId() != eventResult.getHorseId() ){
            eventResult.setHorseId(eventResultChange.getHorseId());
            eventResultRepository.save(eventResult);
        }else if(eventResultChange.getPointsScored()!=null || eventResultChange.getPointsScored()!=eventResult.getPointsScored()){
            eventResult.setPointsScored(eventResultChange.getPointsScored());
            eventResultRepository.save(eventResult);
        }else throw new RuntimeException("Brak zmian");
    }

    public void changeEventType(EventType eventTypeChange){
        EventType eventType = eventTypeRepository.findEventTypeById(eventTypeChange.getEventTypeId()).orElseThrow(()->new EventNotFoundException(format("Event type - %s, not found",eventTypeChange.getName())));

        if(!eventTypeChange.equals(eventType)){
            if(!eventTypeChange.getName().equals(eventType.getName()) || eventTypeChange.getName()!=null){
                eventType.setName(eventTypeChange.getName());
            }else throw new RuntimeException("Brak zmian w nazwie");
            if(!eventTypeChange.getPointsScored().equals(eventType.getPointsScored()) || eventTypeChange.getPointsScored()!=null){
                eventType.setPointsScored(eventTypeChange.getPointsScored());
            }else throw new RuntimeException("Brak zmian w punktach");
            if(!eventTypeChange.getDescription().equals(eventType.getDescription()) || eventTypeChange.getDescription()!=null){
                eventType.setDescription(eventTypeChange.getDescription());
            }else throw new RuntimeException("Brak zmian w opisie");
        }else throw new RuntimeException("Brak zmian w typie wydarzenia");
        eventTypeRepository.save(eventType);
    }

    public EventDeleteResponse deleteEvent(Integer eventId){
        Event eventDelete = eventRepository.findEventById(eventId).orElseThrow(()-> new EventNotFoundException(format("Event result - %s, not found",eventId)));
        eventRepository.deleteById(eventId);

        return new EventDeleteResponse(new Event(
                eventDelete.getEventId(),
                eventDelete.getEventType(),
                eventDelete.getDate()
        ),"Deleted successfull");
    }

    public EventTypeDeleteResponse deleteEventType(Integer eventTypeId){
        EventType eventTypeDelete = eventTypeRepository.findEventTypeById(eventTypeId).orElseThrow(()-> new EventNotFoundException(format("Event result - %s, not found",eventTypeId)));
        eventTypeRepository.deleteById(eventTypeId);

        return new EventTypeDeleteResponse(new EventType(
                eventTypeDelete.getEventTypeId(),
                eventTypeDelete.getName(),
                eventTypeDelete.getDescription(),
                eventTypeDelete.getPointsScored()
        ),"Deleted successfull");
    }

    public EventResultDeleteResponse deleteEventResult(Integer eventResultId){
        EventResult eventResultDelete = eventResultRepository.findEventResultById(eventResultId).orElseThrow(()-> new EventNotFoundException(format("Event result - %s, not found",eventResultId)));
        eventResultRepository.deleteById(eventResultId);

        return new EventResultDeleteResponse(new EventResult(
                eventResultDelete.getId(),
                eventResultDelete.getHorseId(),
                eventResultDelete.getPointsScored()
        ),"Deleted successfull");
    }
}
