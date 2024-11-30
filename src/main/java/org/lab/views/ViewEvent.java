package org.lab.views;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import org.lab.exceptions.EntityNotFoundException;
import org.lab.models.Event;
import org.lab.models.Participant;
import org.lab.services.EventService;
import org.lab.services.ParticipantService;

import java.io.Serializable;
import java.util.List;

@RequestScoped
@Named
public class ViewEvent implements Serializable {

    private EventService eventService;
    private ParticipantService participantService;
    private Long id;

    @EJB
    public void setEventService(EventService service) {
        this.eventService = service;
    }

    @EJB
    public void setParticipantService(ParticipantService service) {
        this.participantService = service;
    }

    public Event getEvent() {
        try {
            return eventService.getEvent(id);
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Participant> getParticipants() throws EntityNotFoundException {
        return participantService.getParticipantsByEvent(id);
    }

    public void deleteParticipant(Long participantId) throws EntityNotFoundException {
        participantService.deleteParticipant(participantId);
    }

}
