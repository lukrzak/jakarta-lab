package org.lab.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.lab.exceptions.EntityNotFoundException;
import org.lab.models.Participant;
import org.lab.repositories.ParticipantRepository;

@ApplicationScoped
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    public ParticipantService() {
        throw new RuntimeException("Incorrect initialization");
    }

    @Inject
    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
        System.out.println("Participants initialized correctly");
        participantRepository.getParticipants()
                .forEach(System.out::println);
    }

    public boolean createParticipant(String email) {
        long numberOfParticipants = participantRepository.getParticipants().size();
        Participant participant = new Participant(++numberOfParticipants, email);

        return participantRepository.addParticipant(participant);
    }

    public void deleteParticipant(Long id) throws EntityNotFoundException {
        if (!participantRepository.deleteParticipant(id)) {
            throw new EntityNotFoundException(id);
        }
    }

}
