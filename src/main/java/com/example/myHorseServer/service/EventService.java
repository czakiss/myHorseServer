package com.example.myHorseServer.service;


import com.example.myHorseServer.dto.event.*;
import com.example.myHorseServer.exception.EventNotFoundException;
import com.example.myHorseServer.model.Event;
import com.example.myHorseServer.model.EventResult;
import com.example.myHorseServer.model.EventType;
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
        creator.setEventTypeName(eventType.getEventTypeName());
        creator.setDescription(eventType.getDescription());
        creator.setPointsScored(eventType.getPointsScored());
        creator = eventTypeRepository.save(creator);

        return new EventTypeCreateResponse(new EventType(
               creator.getEventTypeId(),
                creator.getEventTypeName(),
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
                creator.getEventResultId(),
                creator.getEventId(),
                creator.getHorseId(),
                creator.getPointsScored()
        ),"Create new event result - successfull");
    }

    public Iterable<Event> findAllEvent(){
        return eventRepository.findAll();
    }

    public Iterable<EventType> findAllEventType(){
        return eventTypeRepository.findAll();
    }

    public Iterable<EventResult> findAllEventResult(){
        return eventResultRepository.findAll();
    }


    public Event findEventById(Integer eventId){
        return eventRepository.findById(eventId).orElseThrow(() -> new EventNotFoundException(format("Event with id - %s, not found", eventId))
        );
    }

    public EventType loadEventTypeById(Integer eventTypeId){
        return eventTypeRepository.findByEventTypeId(eventTypeId).orElseThrow(()-> new EventNotFoundException(format("Event type - %s, not found",eventTypeId)));
    }

    public EventType loadEventTypeByName(String eventTypeName){
        return eventTypeRepository.findByEventTypeName(eventTypeName).orElseThrow(()-> new EventNotFoundException(format("Event type - %s, not found",eventTypeName)));
    }

    public EventResult loadEventResultById(Integer eventResultId){
        return eventResultRepository.findByEventResultId(eventResultId).orElseThrow(()-> new EventNotFoundException(format("Event result - %s, not found",eventResultId)));
    }

    public void changeEvent(Event eventChange){
        Event event = eventRepository.findById(eventChange.getEventId()).orElseThrow(()-> new EventNotFoundException(format("Event result - %s, not found",eventChange.getEventType())));
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
        EventResult eventResult = eventResultRepository.findByEventResultId(eventResultChange.getEventResultId()).orElseThrow(()-> new EventNotFoundException(format("Event result - %s, not found",eventResultChange.getEventResultId())));

        if (eventResultChange.getHorseId()!=null || eventResultChange.getHorseId() != eventResult.getHorseId() ){
            eventResult.setHorseId(eventResultChange.getHorseId());
            eventResultRepository.save(eventResult);
        }else if(eventResultChange.getPointsScored()!=null || eventResultChange.getPointsScored()!=eventResult.getPointsScored()){
            eventResult.setPointsScored(eventResultChange.getPointsScored());
            eventResultRepository.save(eventResult);
        }else throw new RuntimeException("No changes");
    }

    public void changeEventType(EventType eventTypeChange){
        EventType eventType = eventTypeRepository.findByEventTypeId(eventTypeChange.getEventTypeId()).orElseThrow(()->new EventNotFoundException(format("Event type - %s, not found",eventTypeChange.getEventTypeName())));

        if(!eventTypeChange.equals(eventType)){
            if(!eventTypeChange.getEventTypeName().equals(eventType.getEventTypeName()) || eventTypeChange.getEventTypeName()!=null){
                eventType.setEventTypeName(eventTypeChange.getEventTypeName());
            }else throw new RuntimeException("No changes in name");
            if(!eventTypeChange.getPointsScored().equals(eventType.getPointsScored()) || eventTypeChange.getPointsScored()!=null){
                eventType.setPointsScored(eventTypeChange.getPointsScored());
            }else throw new RuntimeException("No changes in points");
            if(!eventTypeChange.getDescription().equals(eventType.getDescription()) || eventTypeChange.getDescription()!=null){
                eventType.setDescription(eventTypeChange.getDescription());
            }else throw new RuntimeException("No changes in description");
        }else throw new RuntimeException("No changes in event type");
        eventTypeRepository.save(eventType);
    }

    public EventDeleteResponse deleteEvent(Integer eventId){
        Event eventDelete = eventRepository.findById(eventId).orElseThrow(()-> new EventNotFoundException(format("Event result - %s, not found",eventId)));
        eventRepository.deleteById(eventId);

        return new EventDeleteResponse(new Event(
                eventDelete.getEventId(),
                eventDelete.getEventType(),
                eventDelete.getDate()
        ),"Deleted event successfull");
    }

    public EventTypeDeleteResponse deleteEventType(Integer eventTypeId){
        EventType eventTypeDelete = eventTypeRepository.findByEventTypeId(eventTypeId).orElseThrow(()-> new EventNotFoundException(format("Event result - %s, not found",eventTypeId)));
        eventTypeRepository.deleteById(eventTypeId);

        return new EventTypeDeleteResponse(new EventType(
                eventTypeDelete.getEventTypeId(),
                eventTypeDelete.getEventTypeName(),
                eventTypeDelete.getDescription(),
                eventTypeDelete.getPointsScored()
        ),"Deleted event type successfull");
    }

    public EventResultDeleteResponse deleteEventResult(Integer eventResultId){
        EventResult eventResultDelete = eventResultRepository.findByEventResultId(eventResultId).orElseThrow(()-> new EventNotFoundException(format("Event result - %s, not found",eventResultId)));
        eventResultRepository.deleteById(eventResultId);

        return new EventResultDeleteResponse(new EventResult(
                eventResultDelete.getEventResultId(),
                eventResultDelete.getEventId(),
                eventResultDelete.getHorseId(),
                eventResultDelete.getPointsScored()

                ),"Deleted event results successfull");
    }
}
