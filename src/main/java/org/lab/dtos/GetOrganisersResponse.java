package org.lab.dtos;

import org.lab.models.Organiser;

import java.util.List;

public class GetOrganisersResponse {

    private List<GetOrganiserResponse> organisers;

    public GetOrganisersResponse(List<Organiser> organisers) {
        this.organisers = organisers.stream().map(GetOrganiserResponse::new).toList();
    }

    public List<GetOrganiserResponse> getOrganisers() {
        return organisers;
    }

}
