package org.lab.repositories;

import org.lab.models.Event;
import org.lab.models.Participant;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MemoryData {

    Event e1 = new Event(1L, "Potężny Poniedziałek w Kwadratowej",LocalDate.of(2024, 1, 1), 10.0f, 1000.0f);
    Event e2 = new Event(2L, "Wyjątkowy Wtorek w Kwadratowej", LocalDate.of(2024, 2, 2), 11.0f, 200.0f);
    Event e3 = new Event(3L, "Szalona Środa w Kwadratowej", LocalDate.of(2024, 3, 3), 12.0f, 200.0f);
    Event e4 = new Event(4L, "Czadowy Czwartek w Kwadratowej", LocalDate.of(2024, 4, 4), 11.0f, 200.0f);
    Event e5 = new Event(5L, "Piątkowy melanż w Kwadratowej", LocalDate.of(2024, 5, 5), 10.0f, 200.0f);
    Event e6 = new Event(6L, "Sroga Sobota w Kwadratowej", LocalDate.of(2024, 6, 6), 15.0f, 200.0f);
    Event e7 = new Event(7L, "Niezła Niedziela w Kwadratowej", LocalDate.of(2024, 7, 7), 5.0f, 200.0f);

    Participant p1 = new Participant(1L, "jedrek@student.pl", e1);
    Participant p2 = new Participant(2L, "tymek@student.pl", e1);
    Participant p3 = new Participant(3L, "adas@student.pl", e1);
    Participant p4 = new Participant(4L, "lukasz@student.pl", e2);
    Participant p5 = new Participant(5L, "Pablo@student.pl", e2);
    Participant p6 = new Participant(6L, "kasai@student.pl", e3);
    Participant p7 = new Participant(7L, "mati@student.pl", e4);
    Participant p8 = new Participant(8L, "krystian@student.pl", e5);
    Participant p9 = new Participant(9L, "bartek.komendant@student.pl", e6);
    Participant p10 = new Participant(10L, "siemaro@student.pl", e7);

    public ArrayList<Event> getEvents() {
        return new ArrayList<>(Arrays.asList(e1, e2, e3, e4, e5, e6, e7));
    }

    public List<Participant> getParticipants() {
        return new ArrayList<>(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10));
    }

}
