package org.lab.views;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.SecurityContext;
import org.lab.exceptions.EntityNotFoundException;
import org.lab.models.Event;
import org.lab.models.Organiser;
import org.lab.models.Participant;
import org.lab.services.EventService;
import org.lab.services.ParticipantService;

import java.io.Serializable;
import java.util.List;

@RequestScoped
@Named
public class ViewEvent implements Serializable {

    @Inject
    private SecurityContext securityContext;
    private boolean authorizedToViewParticipant = false;
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

    public void verify() {
        if (!getEvent().getOrganiser().getName().equals(securityContext.getCallerPrincipal().getName())
                && !securityContext.isCallerInRole(Organiser.Role.ADMIN)) {
            this.authorizedToViewParticipant = false;
        } else {
            this.authorizedToViewParticipant = true;
        }
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

    public boolean isAuthorizedToViewParticipant() {
        return authorizedToViewParticipant;
    }
}
