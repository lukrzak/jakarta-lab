package org.lab.repositories;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.mail.Part;
import org.lab.models.Event;
import org.lab.models.Participant;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Dependent
public class EventRepository {

    private final ParticipantRepository participantRepository;
    private List<Event> events;

    @Inject
    public EventRepository(ParticipantRepository participantRepository, MemoryData memoryData) {
        this.participantRepository = participantRepository;
        this.events = memoryData.getEvents();
    }

    public Optional<Event> getEvent(Long id) {
        System.out.println("ids:");
        events.forEach(e -> System.out.println(e.getId()));
        System.out.println("Id looking: " + id);
        return events.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }

    public void assignParticipants(Event event) {
        List<Participant> participants = participantRepository.getParticipantsByEvent(event.getId());
        event.setParticipants(participants);
    }

    public List<Event> getEvents() {
        events.forEach(e -> {
            List<Participant> filteredParticipants = participantRepository.getParticipantsByEvent(e.getId());
            e.setParticipants(filteredParticipants);
        });
        return new ArrayList<>(events);
    }

    public boolean addEvent(Event event) {
        return events.add(event);
    }

    public boolean deleteEvent(Long id) {
        participantRepository.deleteParticipantByEvent(id);
        return events.removeIf(e -> e.getId().equals(id));
    }

}
