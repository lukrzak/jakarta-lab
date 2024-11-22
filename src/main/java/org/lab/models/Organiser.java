package org.lab.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.lab.dtos.PutOrganiserRequest;

import java.util.UUID;

@Entity
public class Organiser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private float balance;
    private String password;

    public Organiser() {
    }

    public Organiser(UUID id, String name) {
        this(name);
        this.id = id;
    }

    public Organiser(String name) {
        this.name = name;
        this.balance = 0;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
