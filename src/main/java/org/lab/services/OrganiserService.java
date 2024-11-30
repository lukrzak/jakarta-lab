package org.lab.services;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import org.lab.dtos.PutOrganiserRequest;
import org.lab.exceptions.EntityNotFoundException;
import org.lab.models.Organiser;
import org.lab.repositories.OrganiserRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@LocalBean
@Stateless
public class OrganiserService {

    private final OrganiserRepository organiserRepository;

    private final Pbkdf2PasswordHash passwordHash;

    public OrganiserService() {
        this.organiserRepository = null;
        this.passwordHash = null;
    }

    @Inject
    public OrganiserService(OrganiserRepository organiserRepository, Pbkdf2PasswordHash passwordHash) {
        this.organiserRepository = organiserRepository;
        this.passwordHash = passwordHash;
    }

    public List<Organiser> getOrganisers() {
        return organiserRepository.getOrganisers();
    }

    public Optional<Organiser> getOrganiser(UUID uuid) {
        return organiserRepository.getOrganiser(uuid);
    }

    public Optional<Organiser> getOrganiser(String name) {
        return organiserRepository.getOrganiser(name);
    }

    public void addOrganiser(PutOrganiserRequest request) {
        Organiser organiser = new Organiser(request.getOrganiserName());
        organiser.setPassword(passwordHash.generate(request.getPassword().toCharArray()));
        organiser.setRole(Organiser.Role.USER);
        organiserRepository.addOrganiser(organiser);
    }

    public void addOrganiser(Organiser organiser) {
        organiserRepository.addOrganiser(organiser);
    }

    public boolean verify(String name, String password) {
        return getOrganiser(name)
                .map(organiser -> passwordHash.verify(password.toCharArray(), organiser.getPassword()))
                .orElse(false);
    }

//    public List<Organiser> findAll() {
//        return organiserRepository.findAll();
//    }
//
//    public Organiser findById(UUID id) throws EntityNotFoundException {
//        return organiserRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Organiser " + id.toString()));
//    }
//
//    public byte[] getOrganiserLogo(UUID id) throws IOException {
//        return organiserRepository.getOrganiserLogo(id);
//    }
//
//    public void putOrganiserLogo(UUID id, InputStream logo) throws IOException {
//        organiserRepository.putOrganiserLogo(id, logo);
//    }
//
//    public boolean deleteOrganiserLogo(UUID id) {
//        return organiserRepository.deleteOrganiserLogo(id);
//    }

}
