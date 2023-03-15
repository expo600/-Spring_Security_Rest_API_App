package com.ryzhov_andrei.spring_security_rest_api_app.service;

import com.ryzhov_andrei.spring_security_rest_api_app.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventService extends GenericService<Event,Long> {
    Event create(Event event);
    Event update(Event event);
}
