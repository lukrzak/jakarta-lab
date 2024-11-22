package org.lab.dtos;

import org.lab.models.Organiser;
import java.util.UUID;

public class GetOrganiserResponse {

    private final UUID id;
    private final String name;

    public GetOrganiserResponse(Organiser organiser) {
        id = organiser.getId();
        name = organiser.getName();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
