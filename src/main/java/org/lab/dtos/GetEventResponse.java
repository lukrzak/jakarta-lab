package org.lab.dtos;

import org.lab.models.Event;
import org.lab.models.Participant;

import java.time.LocalDate;
import java.util.List;

public class GetEventResponse {

    private Long id;
    private String name;
    private LocalDate startDate;
    private float ticketPrice;
    private float totalCost;
    private List<String> participants;

    public GetEventResponse(Event event) {
        this.id = event.getId();
        this.name = event.getName();
        this.startDate = event.getStartDate();
        this.ticketPrice = event.getTicketPrice();
        this.totalCost = event.getTotalCost();
        this.participants = event.getParticipants().stream().map(Participant::getEmail).toList();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public float getTicketPrice() {
        return ticketPrice;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public List<String> getParticipants() {
        return participants;
    }

}
