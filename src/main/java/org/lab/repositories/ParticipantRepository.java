package org.lab.repositories;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import org.lab.exceptions.EntityNotFoundException;
import org.lab.models.Event;
import org.lab.models.Participant;

import java.util.List;
import java.util.Optional;

@Dependent
public class ParticipantRepository {

    private final List<Participant> participants;

    @Inject
    public ParticipantRepository(MemoryData memoryData) {
        this.participants = memoryData.getParticipants();
    }

    public Optional<Participant> getParticipant(Long id) {
        return participants.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }

    public List<Participant> getParticipants() {
        return List.copyOf(participants);
    }

    public boolean addParticipant(Participant participant) {
        return participants.add(participant);
    }

    public boolean deleteParticipant(Long id) {
        return participants.removeIf(e -> e.getId().equals(id));
    }

    public boolean deleteParticipantByEvent(Long eventId) {
        return participants.removeIf(e -> e.getEvent().getId().equals(eventId));
    }

    public List<Participant> getParticipantsByEvent(Long eventId) {
        return participants.stream().filter(e -> e.getEvent().getId().equals(eventId)).toList();
    }

    public void updateParticipant(Long id, Participant participant) throws EntityNotFoundException {
        Participant p = participants.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException(id));
        p.setPaymentStatus(participant.getPaymentStatus());
        p.setEmail(participant.getEmail());
    }
}
