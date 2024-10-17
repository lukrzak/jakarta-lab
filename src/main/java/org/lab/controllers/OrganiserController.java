package org.lab.controllers;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import org.lab.dtos.GetOrganiserResponse;
import org.lab.dtos.GetOrganisersResponse;
import org.lab.exceptions.EntityNotFoundException;
import org.lab.models.Organiser;
import org.lab.services.OrganiserService;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@RequestScoped
public class OrganiserController {

    private final OrganiserService organiserService;

    public OrganiserController() {
        this.organiserService = null;
    }

    @Inject
    public OrganiserController(OrganiserService organiserService) {
        this.organiserService = organiserService;
    }

    public GetOrganisersResponse getOrganisers() {
        return new GetOrganisersResponse(organiserService.findAll());
    }

    public GetOrganiserResponse getOrganiser(UUID id) {
        try {
            Organiser organiser = organiserService.findById(id);
            return new GetOrganiserResponse(organiser);
        } catch (EntityNotFoundException e) {
            return null;
        }
    }

    public byte[] getOrganiserLogo(UUID id) throws IOException {
        byte[] logo = organiserService.getOrganiserLogo(id);
        if (logo == null) {
            throw new IOException();
        }
        return logo;
    }

    public void putOrganiserLogo(UUID id, InputStream logo) throws IOException {
        organiserService.putOrganiserLogo(id, logo);
    }

    public boolean deleteOrganiserLogo(UUID id) {
        return organiserService.deleteOrganiserLogo(id);
    }

}
