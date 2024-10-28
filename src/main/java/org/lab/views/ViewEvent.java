package org.lab.views;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.lab.exceptions.EntityNotFoundException;
import org.lab.models.Event;
import org.lab.models.Participant;
import org.lab.services.EventService;
import org.lab.services.ParticipantService;

import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named
public class ViewEvent implements Serializable {

    private final EventService eventService;
    private final ParticipantService participantService;
    private Long id;

    @Inject
    public ViewEvent(EventService eventService, ParticipantService participantService) {
        System.out.println("View event initialized");
        this.eventService = eventService;
        this.participantService = participantService;
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

    public List<Participant> getParticipants() {
        return participantService.getParticipantsByEvent(id);
    }

    public void deleteParticipant(Long participantId) throws EntityNotFoundException {
        participantService.deleteParticipant(participantId);
    }

}
