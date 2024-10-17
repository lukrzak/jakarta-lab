package org.lab.repositories;

import jakarta.enterprise.context.Dependent;
import org.lab.models.Event;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Dependent
public class EventRepository {

    private final List<Event> events = List.of(
            new Event(1L, "Potężny Poniedziałek w Kwadratowej", LocalDate.of(2024, 1, 1), 10.0f, 1000.0f),
            new Event(2L, "Wyjątkowy Wtorek w Kwadratowej", LocalDate.of(2024, 2, 2), 11.0f, 200.0f),
            new Event(3L, "Szalona Środa w Kwadratowej", LocalDate.of(2024, 3, 3), 12.0f, 200.0f),
            new Event(4L, "Czadowy Czwartek w Kwadratowej", LocalDate.of(2024, 4, 4), 11.0f, 200.0f),
            new Event(5L, "Piątkowy melanż w Kwadratowej", LocalDate.of(2024, 5, 5), 10.0f, 200.0f),
            new Event(6L, "Sroga Sobota w Kwadratowej", LocalDate.of(2024, 6, 6), 15.0f, 200.0f),
            new Event(7L, "Niezła Niedziela w Kwadratowej", LocalDate.of(2024, 7, 7), 5.0f, 200.0f)
    );

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
        return events.removeIf(e -> e.getId().equals(id));
    }

}
