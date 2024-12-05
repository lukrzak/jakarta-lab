package org.lab.dtos;

import org.lab.models.Participant;
import org.lab.models.PaymentStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class GetParticipantResponse {

    private Long id;
    private String email;
    private PaymentStatus paymentStatus;

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

    private LocalDateTime created;
    private LocalDateTime updated;

    public GetParticipantResponse(Participant participant) {
        this.id = participant.getId();
        this.email = participant.getEmail();
        this.paymentStatus = participant.getPaymentStatus();
        this.created = participant.getCreated();
        this.updated = participant.getUpdated();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

}
