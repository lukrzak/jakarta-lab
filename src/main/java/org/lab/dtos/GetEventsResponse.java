package org.lab.dtos;

import org.lab.models.Event;

import java.util.ArrayList;
import java.util.List;

public class GetEventsResponse {

    private List<GetEventResponse> events = new ArrayList<>();
    private int totalParticipants = 0;

    public GetEventsResponse(List<Event> events) {
        events.forEach(e ->  {
            this.events.add(new GetEventResponse(e));
            this.totalParticipants += e.getParticipants().size();
        });
    }

    public List<GetEventResponse> getEvents() {
        return events;
    }

    public int getTotalParticipants() {
        return totalParticipants;
    }

}
