package org.lab.models;

import org.lab.dtos.PutEventRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Event {

    private List<Participant> participants = new ArrayList<>();
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

    public Event(Long id, PutEventRequest request) {
        this.id = id;
        this.name = request.getName();
        this.startDate = request.getStartDate();
        this.ticketPrice = request.getTicketPrice();
        this.totalCost = request.getTotalCost();
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

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setTicketPrice(float ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

}
