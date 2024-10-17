package org.lab.models;

public class Participant {

    private final Long id;
    private final String email;
    private PaymentStatus paymentStatus;

    public Participant(Long participantId, String email) {
        this.id = participantId;
        this.email = email;
        this.paymentStatus = PaymentStatus.UNPAID;
    }

    public Long getId() {
        return id;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

}
