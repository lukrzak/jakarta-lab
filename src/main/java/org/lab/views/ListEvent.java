package org.lab.views;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import org.lab.exceptions.EntityNotFoundException;
import org.lab.models.Event;
import org.lab.services.EventService;

import java.util.List;

@RequestScoped
@Named
public class ListEvent {

    private EventService eventService;

    @EJB
    public void setEventService(EventService service) {
        System.out.println("TEST");
        this.eventService = service;
    }

    public List<Event> getEvents() {
        return eventService.getEvents();
    }

    public void deleteEvent(Long id) throws EntityNotFoundException {
        eventService.deleteEvent(id);
    }

}