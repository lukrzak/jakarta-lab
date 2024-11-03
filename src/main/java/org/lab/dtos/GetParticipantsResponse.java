package org.lab.dtos;

import org.lab.models.Participant;

import java.util.ArrayList;
import java.util.List;

public class GetParticipantsResponse {

    private List<GetParticipantResponse> participants = new ArrayList<>();

    public GetParticipantsResponse(List<Participant> participants) {
        participants.forEach(p -> this.participants.add(new GetParticipantResponse(p)));
    }

    public List<GetParticipantResponse> getParticipants() {
        return participants;
    }

    public void setParticipants(List<GetParticipantResponse> participants) {
        this.participants = participants;
    }

}
