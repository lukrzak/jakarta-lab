package org.lab.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.lab.exceptions.EntityNotFoundException;
import org.lab.models.Participant;
import org.lab.repositories.ParticipantRepository;

import java.util.List;

@ApplicationScoped
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    public ParticipantService() {
        participantRepository = null;
    }

    @Inject
    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
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

    public void updateParticipant(Long id, Participant participant) throws EntityNotFoundException {
        participantRepository.updateParticipant(id, participant);
    }
}
