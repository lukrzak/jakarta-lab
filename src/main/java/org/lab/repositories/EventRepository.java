package org.lab.repositories;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import org.lab.models.Event;

import java.util.List;
import java.util.Optional;

@Dependent
public class EventRepository {

    private final ParticipantRepository participantRepository;
    private final List<Event> events;

    @Inject
    public EventRepository(ParticipantRepository participantRepository, MemoryData memoryData) {
        this.participantRepository = participantRepository;
        this.events = memoryData.getEvents();
    }

    public Optional<Event> getEvent(Long id) {
        return events.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }

    public List<Event> getEvents() {
        return List.copyOf(events);
    }

    public boolean addEvent(Event event) {
        return events.add(event);
    }

    public boolean deleteEvent(Long id) {
        participantRepository.deleteParticipantByEvent(id);
        return events.removeIf(e -> e.getId().equals(id));
    }

}
