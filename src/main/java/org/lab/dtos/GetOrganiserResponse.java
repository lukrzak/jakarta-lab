package org.lab.dtos;

import org.lab.models.Event;
import org.lab.models.Organiser;

import java.util.List;
import java.util.UUID;

public class GetOrganiserResponse {

    private final UUID id;
    private final String name;
    private final List<String> events;

    public GetOrganiserResponse(Organiser organiser) {
        id = organiser.getId();
        name = organiser.getName();
        events = organiser.getEvents().stream().map(Event::getName).toList();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getEvents() {
        return events;
    }

}
