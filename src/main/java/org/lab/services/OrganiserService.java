package org.lab.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.lab.exceptions.EntityNotFoundException;
import org.lab.models.Organiser;
import org.lab.repositories.OrganiserRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class OrganiserService {

    private final OrganiserRepository organiserRepository;

    public OrganiserService() {
        this.organiserRepository = null;
    }

    @Inject
    public OrganiserService(OrganiserRepository organiserRepository) {
        this.organiserRepository = organiserRepository;
    }

    public List<Organiser> findAll() {
        return organiserRepository.findAll();
    }

    public Organiser findById(UUID id) throws EntityNotFoundException {
        return organiserRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Organiser " + id.toString()));
    }

    public byte[] getOrganiserLogo(UUID id) throws IOException {
        return organiserRepository.getOrganiserLogo(id);
    }

    public void putOrganiserLogo(UUID id, InputStream logo) throws IOException {
        organiserRepository.putOrganiserLogo(id, logo);
    }

    public boolean deleteOrganiserLogo(UUID id) {
        return organiserRepository.deleteOrganiserLogo(id);
    }

}
