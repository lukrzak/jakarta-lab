package org.lab.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Event {

    private final List<Participant> participants = new ArrayList<>();
    private Long id;
    private String name;
    private LocalDate startDate;
    private float ticketPrice;
    private float totalCost;

    public Event(Long id, String name, LocalDate startDate, float ticketPrice, float totalCost) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.ticketPrice = ticketPrice;
        this.totalCost = totalCost;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Participant> getParticipants() {
        return List.copyOf(participants);
    }

    public float getTicketPrice() {
        return ticketPrice;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

}
