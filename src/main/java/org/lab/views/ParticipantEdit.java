package org.lab.views;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.lab.exceptions.EntityNotFoundException;
import org.lab.models.Participant;
import org.lab.services.ParticipantService;

@RequestScoped
@Named
public class ParticipantEdit {

    private final ParticipantService participantService;
    private Long id;
    private Participant participant;

    @Inject
    public ParticipantEdit(ParticipantService participantService) {
        this.participantService = participantService;
    }

    public void load() throws EntityNotFoundException {
        this.participant = participantService.getParticipant(id);
    }

    public void update() {

    }

}
