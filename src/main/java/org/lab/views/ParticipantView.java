package org.lab.views;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.lab.exceptions.EntityNotFoundException;
import org.lab.models.Participant;
import org.lab.services.ParticipantService;

import java.io.Serializable;

@ViewScoped
@Named
public class ParticipantView implements Serializable {

    private final ParticipantService participantService;
    private Long id;
    private Participant participant;

    @Inject
    public ParticipantView(ParticipantService participantService) {
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
