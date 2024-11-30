package org.lab.views;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import org.lab.exceptions.EntityNotFoundException;
import org.lab.models.Participant;
import org.lab.services.ParticipantService;

import java.io.Serializable;

@RequestScoped
@Named
public class ParticipantView implements Serializable {

    private ParticipantService participantService;
    private Long id;
    private Participant participant;

    @EJB
    public void setParticipantService(ParticipantService participantService) {
        this.participantService = participantService;
    }

    public void load() throws EntityNotFoundException {
        this.participant = participantService.getParticipant(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Participant getParticipant() {
        return participant;
    }
}
