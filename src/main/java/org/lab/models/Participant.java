package org.lab.models;

import org.lab.dtos.PutParticipantRequest;

public class Participant {

    private final Long id;
    private String email;
    private PaymentStatus paymentStatus;
    private Event event;

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
