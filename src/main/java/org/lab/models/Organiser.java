package org.lab.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Organiser {

    private UUID id;
    private final List<Event> events = new ArrayList<>();
    private final String name;
    private float balance;

    public Organiser(UUID id, String name) {
        this(name);
        this.id = id;
    }

    public Organiser(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public List<Event> getEvents() {
        return List.copyOf(events);
    }

    public String getName() {
        return name;
    }
}
