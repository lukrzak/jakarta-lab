package org.lab.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Event {

    private final List<Participant> participants = new ArrayList<>();
    private String name;
    private LocalDate startDate;
    private float ticketPrice;
    private float totalCost;

    public String getName() {
        return name;
    }
}
