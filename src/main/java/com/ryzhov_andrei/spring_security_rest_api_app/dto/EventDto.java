package com.ryzhov_andrei.spring_security_rest_api_app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ryzhov_andrei.spring_security_rest_api_app.model.Event;
import com.ryzhov_andrei.spring_security_rest_api_app.model.File;
import com.ryzhov_andrei.spring_security_rest_api_app.model.Status;
import com.ryzhov_andrei.spring_security_rest_api_app.model.User;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventDto {
    private Long id;
    private User user;
    private File file;


    public Event toEvent() {
        Event event = new Event();
        event.setId(id);
        event.setUser(user);
        event.setFile(file);
        return event;
    }

    public static EventDto fromEvent(Event event) {
        if (Objects.isNull(event)) {
            return null;
        }
        EventDto eventDto = new EventDto();
        eventDto.setId(event.getId());
        eventDto.setUser(event.getUser());
        eventDto.setFile(event.getFile());
        return eventDto;
    }

    public static List<EventDto> toEventDtos(List<Event> events) {
        List<EventDto> eventDtoList = new ArrayList<>();
        for (Event event: events) {
            eventDtoList.add(fromEvent(event));
        }
        return eventDtoList;
    }
}
