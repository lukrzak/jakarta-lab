package org.lab.views;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.security.enterprise.SecurityContext;
import org.lab.exceptions.EntityNotFoundException;
import org.lab.models.Organiser;
import org.lab.models.Participant;
import org.lab.services.ParticipantService;

import java.io.Serializable;

@RequestScoped
@Named
public class ParticipantView implements Serializable {

    private ParticipantService participantService;
    private Long id;
    private Participant participant;
    private boolean authorizedToViewParticipant = false;
    private SecurityContext securityContext;

    public ParticipantView() {
    }

    @EJB
    public void setSecurityContext(SecurityContext securityContext) {
        this.securityContext = securityContext;
    }

    @EJB
    public void setParticipantService(ParticipantService participantService) {
        this.participantService = participantService;
    }

    public void load() throws EntityNotFoundException {
        this.participant = participantService.getParticipant(id);
        if (participant != null) {
            if (!participant.getEvent().getOrganiser().getName().equals(securityContext.getCallerPrincipal().getName())
                    && !securityContext.isCallerInRole(Organiser.Role.ADMIN)) {
                this.authorizedToViewParticipant = false;
            } else {
                this.authorizedToViewParticipant = true;
            }
        }
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

    public boolean isAuthorizedToViewParticipant() {
        return authorizedToViewParticipant;
    }
}
