package org.lab.services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.lab.dtos.PatchEventRequest;
import org.lab.dtos.PutEventRequest;
import org.lab.exceptions.EntityNotFoundException;
import org.lab.models.Event;
import org.lab.models.Organiser;
import org.lab.models.Participant;
import org.lab.repositories.EventRepository;

import java.util.List;

@LocalBean
@Stateless
public class EventService {

    private final EventRepository eventRepository;
    private final OrganiserService organiserService;
    private final ParticipantService participantService;

    public EventService() {
        this.eventRepository = null;
        this.organiserService = null;
        this.participantService = null;
    }

    @Inject
    public EventService(EventRepository eventRepository, OrganiserService organiserService, ParticipantService participantService) {
        this.eventRepository = eventRepository;
        this.organiserService = organiserService;
        this.participantService = participantService;
    }

    public List<Event> getEvents() {
        return eventRepository.getEvents();
    }

    public List<Event> getEvents(String username) {
        return eventRepository.getEvents(username);
    }

    public Event getEvent(Long id) throws EntityNotFoundException {
        return eventRepository.getEvent(id)
                .orElseThrow(() -> new EntityNotFoundException(id));
    }

    public void modifyEvent(Long id, PatchEventRequest request) throws EntityNotFoundException {
        Event eventToModify = eventRepository.getEvent(id).orElseThrow(() -> new EntityNotFoundException(id));
        eventToModify.setName(request.getName());
        eventToModify.setStartDate(request.getStartDate());
        eventToModify.setTicketPrice(request.getTicketPrice());
        eventRepository.updateEvent(eventToModify);
    }

    public void createEvent(PutEventRequest request) {
        Event event = new Event(request.getName(), request.getStartDate(), request.getTicketPrice(), request.getTotalCost());
        Organiser organiser = organiserService.getOrganiser(request.getOrganiserName()).orElse(null);
        event.setOrganiser(organiser);
        eventRepository.addEvent(event);


        Participant p1 = new Participant("email1@gmail.com", event);
        Participant p2 = new Participant("email2@gmail.com", event);
        participantService.addParticipant(p1);
        participantService.addParticipant(p2);
    }

    public void deleteEvent(Long eventId) throws EntityNotFoundException {
        eventRepository.deleteEvent(eventId);
    }

}
