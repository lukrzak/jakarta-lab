package org.lab.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.lab.dtos.PatchParticipantRequest;
import org.lab.dtos.PutParticipantRequest;
import org.lab.exceptions.EntityNotFoundException;
import org.lab.models.Event;
import org.lab.models.Participant;
import org.lab.repositories.EventRepository;
import org.lab.repositories.ParticipantRepository;

import java.util.List;

@ApplicationScoped
public class ParticipantService {

    private final ParticipantRepository participantRepository;
    private final EventRepository eventRepository;

    public ParticipantService() {
        participantRepository = null;
        eventRepository = null;
    }

    @Inject
    public ParticipantService(ParticipantRepository participantRepository, EventRepository eventRepository) {
        this.participantRepository = participantRepository;
        this.eventRepository = eventRepository;
    }

    public Participant getParticipant(Long id) throws EntityNotFoundException {
        return participantRepository.getParticipant(id).orElseThrow(() -> new EntityNotFoundException(id));
    }

    public List<Participant> getParticipantsByEvent(Long id) throws EntityNotFoundException {
        Event event = eventRepository.getEvent(id).orElseThrow(() -> new EntityNotFoundException(id));
        return participantRepository.getParticipantsByEvent(event);
    }

    @Transactional
    public void addParticipant(Long event_id, PutParticipantRequest request) throws EntityNotFoundException {
        Event event = eventRepository.getEvent(event_id).orElseThrow(() -> new EntityNotFoundException(event_id));
        Participant participant = new Participant(request.getEmail(), event);
        participantRepository.addParticipant(participant);
    }

    @Transactional
    public void deleteParticipant(Long id) throws EntityNotFoundException {
        participantRepository.deleteParticipant(id);
    }

    @Transactional
    public void modifyParticipant(Long id, PatchParticipantRequest request) throws EntityNotFoundException {
        Participant participant = participantRepository.getParticipant(id).orElseThrow(() -> new EntityNotFoundException(id));
        participant.setEmail(request.getEmail());
        participant.setPaymentStatus(request.getPaymentStatus());
        participantRepository.updateParticipant(participant);
    }

}
