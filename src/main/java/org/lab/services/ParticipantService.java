package org.lab.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
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
        System.out.println("Participants initialized correctly");
        participantRepository.getParticipants()
                .forEach(System.out::println);
    }

//    public boolean createParticipant(String email) {
//        long numberOfParticipants = participantRepository.getParticipants().size();
//        //Participant participant = new Participant(++numberOfParticipants, email);
//
//        return participantRepository.addParticipant(participant);
//    }

    public void deleteParticipant(Long id) throws EntityNotFoundException {
        if (!participantRepository.deleteParticipant(id)) {
            throw new EntityNotFoundException(id);
        }
    }

    public List<Participant> getParticipantsByEvent(Long id) {
        return participantRepository.getParticipantsByEvent(id);
    }

    public Participant getParticipant(Long id) throws EntityNotFoundException {
        return participantRepository.getParticipant(id).orElseThrow(() -> new EntityNotFoundException(id));
    }

    public boolean addParticipant(Long event_id, Long id, PutParticipantRequest request) throws EntityNotFoundException {
        Event event = eventRepository.getEvent(event_id).orElseThrow(() -> new EntityNotFoundException(event_id));
        Participant participant = new Participant(id, request, event);
        return participantRepository.addParticipant(participant);
    }

    public void modifyParticipant(Long id, PatchParticipantRequest request) throws EntityNotFoundException {
        Participant participant = participantRepository.getParticipant(id).orElseThrow(() -> new EntityNotFoundException(id));
        participant.setEmail(request.getEmail());
        participant.setPaymentStatus(request.getPaymentStatus());

    }

    public void updateParticipant(Long id, Participant participant) throws EntityNotFoundException {
        participantRepository.updateParticipant(id, participant);
    }

}
