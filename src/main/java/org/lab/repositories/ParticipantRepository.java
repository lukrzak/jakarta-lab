package org.lab.repositories;

import jakarta.enterprise.context.Dependent;
import org.lab.models.Participant;

import java.util.List;
import java.util.Optional;

@Dependent
public class ParticipantRepository {

    private final List<Participant> participants = List.of(
            new Participant(1L, "jedrek@student.pl"),
            new Participant(2L, "tymek@student.pl"),
            new Participant(3L, "adas@student.pl"),
            new Participant(4L, "lukasz@student.pl"),
            new Participant(5L, "Pablo@student.pl"),
            new Participant(6L, "kasai@student.pl"),
            new Participant(7L, "mati@student.pl"),
            new Participant(8L, "krystian@student.pl"),
            new Participant(9L, "bartek.komendant@student.pl"),
            new Participant(10L, "siemaro@student.pl")
    );

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

}
