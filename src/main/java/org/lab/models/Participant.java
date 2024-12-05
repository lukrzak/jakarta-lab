package org.lab.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.lab.dtos.PutParticipantRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String email;
    private PaymentStatus paymentStatus;
    @ManyToOne
    @JoinColumn(name = "event")
    private Event event;


    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    private LocalDateTime created = LocalDateTime.now();
    private LocalDateTime updated;

    @PrePersist
    private void prePersist() {
        created = LocalDateTime.now();
        updated = LocalDateTime.now();
    }

    @PreUpdate
    private void preUpdate() {
        updated = LocalDateTime.now();
    }

    public Participant() {
    }

    public Participant(String email, Event event) {
        this.email = email;
        this.event = event;
        this.paymentStatus = PaymentStatus.UNPAID;
    }

    public Participant(Long participantId, String email, Event event) {
        this.id = participantId;
        this.email = email;
        this.paymentStatus = PaymentStatus.UNPAID;
        this.event = event;
    }

    public Participant(Long id, PutParticipantRequest request, Event event) {
        this.id = id;
        this.email = request.getEmail();
        this.paymentStatus = PaymentStatus.UNPAID;
        this.event = event;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public Event getEvent() {
        return event;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
