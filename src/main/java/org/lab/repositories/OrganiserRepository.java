package org.lab.repositories;

import org.lab.models.Organiser;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class OrganiserRepository {

    private final List<Organiser> organisers = List.of(
        new Organiser(UUID.fromString("11111111-2222-3333-4444-555555555555"), "Organisers inc."),
        new Organiser(UUID.fromString("12345678-1234-1234-1234-123456789098"), "PGboiz"),
        new Organiser(UUID.fromString("87654321-4321-4321-4321-890987654321"), "TuddyBuddy"),
        new Organiser(UUID.fromString("AAAAAAAA-BBBB-CCCC-DDDD-EEEEEEEEEEEE"), "Inators co.")
    );

    public Optional<Organiser> findById(UUID id) {
        return organisers.stream()
                .filter(o -> o.getId().equals(id))
                .findFirst();
    }

    public List<Organiser> findAll() {
        return List.copyOf(organisers);
    }

}
