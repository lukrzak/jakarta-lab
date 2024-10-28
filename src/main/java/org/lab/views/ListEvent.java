package org.lab.views;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import org.lab.exceptions.EntityNotFoundException;
import org.lab.models.Event;
import org.lab.services.EventService;

import java.util.List;

@RequestScoped
@Named
public class ListEvent {

    private final EventService eventService;

    @Inject
    public ListEvent(EventService eventService) {
        this.eventService = eventService;
    }

    public List<Event> getEvents() {
        return eventService.getEvents();
    }

    public void deleteEvent(Long id) throws EntityNotFoundException {
        eventService.deleteEvent(id);
    }

}