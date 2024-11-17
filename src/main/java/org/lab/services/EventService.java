package org.lab.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.lab.dtos.PatchEventRequest;
import org.lab.dtos.PutEventRequest;
import org.lab.exceptions.EntityNotFoundException;
import org.lab.models.Event;
import org.lab.repositories.EventRepository;

import java.util.List;

@ApplicationScoped
public class EventService {

    private final EventRepository eventRepository;

    public EventService() {
        this.eventRepository = null;
    }

    @Inject
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public List<Event> getEvents() {
        return eventRepository.getEvents();
    }

    public Event getEvent(Long id) throws EntityNotFoundException {
        return eventRepository.getEvent(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

    @Transactional
    public void modifyEvent(Long id, PatchEventRequest request) throws EntityNotFoundException {
        Event eventToModify = eventRepository.getEvent(id).orElseThrow(() -> new EntityNotFoundException(id));
        eventToModify.setName(request.getName());
        eventToModify.setStartDate(request.getStartDate());
        eventToModify.setTicketPrice(request.getTicketPrice());
        eventRepository.updateEvent(eventToModify);
    }

    @Transactional
    public void createEvent(PutEventRequest request) {
        Event event = new Event(request.getName(), request.getStartDate(), request.getTicketPrice(), request.getTotalCost());
        eventRepository.addEvent(event);
    }

    @Transactional
    public void deleteEvent(Long eventId) throws EntityNotFoundException {
        eventRepository.deleteEvent(eventId);
    }

}
