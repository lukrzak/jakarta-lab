package org.lab.repositories;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import org.lab.models.Organiser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Dependent
public class OrganiserRepository {

    private final String logoStoragePath;
    private final String PNG_EXTENSION = ".png";
    private final List<Organiser> organisers = List.of(
        new Organiser(UUID.fromString("11111111-2222-3333-4444-555555555555"), "Organisers inc."),
        new Organiser(UUID.fromString("12345678-1234-1234-1234-123456789098"), "PGboiz"),
        new Organiser(UUID.fromString("87654321-4321-4321-4321-890987654321"), "TuddyBuddy"),
        new Organiser(UUID.fromString("AAAAAAAA-BBBB-CCCC-DDDD-EEEEEEEEEEEE"), "Inators co.")
    );

    public OrganiserRepository() {
        this.logoStoragePath = System.getenv("STORAGE_PATH");
        System.out.println("Storage path: " + logoStoragePath);
    }

    public Optional<Organiser> findById(UUID id) {
        return organisers.stream()
                .filter(o -> o.getId().equals(id))
                .findFirst();
    }

    public List<Organiser> findAll() {
        return List.copyOf(organisers);
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
        final File targetFile = new File(logoStoragePath + id + PNG_EXTENSION);
        Files.copy(logo, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    public boolean deleteOrganiserLogo(UUID id) {
        File myObj = new File(logoStoragePath + id.toString() + PNG_EXTENSION);
        System.out.println(logoStoragePath + id + PNG_EXTENSION);
        return myObj.delete();
    }

}
