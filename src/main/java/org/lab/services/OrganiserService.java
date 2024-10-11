package org.lab.services;

import org.lab.exceptions.EntityNotFoundException;
import org.lab.models.Organiser;
import org.lab.repositories.OrganiserRepository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

public class OrganiserService {

    private String logoStoragePath;
    private final String PNG_EXTENSION = ".png";
    private final OrganiserRepository organiserRepository;

    public OrganiserService(OrganiserRepository organiserRepository, String storagePath) {
        this.organiserRepository = organiserRepository;
        this.logoStoragePath = storagePath;
        System.out.println("Storage path set to: " + this.logoStoragePath);
    }

    public List<Organiser> findAll() {
        return organiserRepository.findAll();
    }

    public Organiser findById(UUID id) throws EntityNotFoundException {
        return organiserRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Organiser " + id.toString()));
    }

    public byte[] getOrganiserLogo(UUID id) throws IOException {
        System.out.println(logoStoragePath + id.toString() + PNG_EXTENSION);
        if (new File(logoStoragePath + id + PNG_EXTENSION).exists()) {
            return Files.readAllBytes(Paths.get(logoStoragePath + id + PNG_EXTENSION));
        }
        return null;
    }

    public void putOrganiserLogo(UUID id, InputStream logo) throws IOException {
        System.out.println(logoStoragePath + id.toString() + PNG_EXTENSION);
        final File targetFile = new File(logoStoragePath + id.toString() + PNG_EXTENSION);
        Files.copy(logo, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    public boolean deleteOrganiserLogo(UUID id) {
        File myObj = new File(logoStoragePath + id.toString() + PNG_EXTENSION);
        System.out.println(logoStoragePath + id.toString() + PNG_EXTENSION);
        return myObj.delete();
    }

}
