package org.lab.models;

import jakarta.persistence.*;
import org.lab.dtos.PutOrganiserRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Organiser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private float balance;
    private String password;
    @Column(name = "role")
    @CollectionTable(name = "users_roles", joinColumns = @JoinColumn(name = "id"))
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> role = new ArrayList<>();

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

    public void setRole(String role) {
        this.role.add(role);
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

    public class Role {
        public static final String ADMIN = "ADMIN";
        public static final String USER = "USER";
    }
}
