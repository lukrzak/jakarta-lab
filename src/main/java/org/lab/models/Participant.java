package org.lab.models;

public class Participant {

    private final Long participantId;
    private String email;
    private PaymentStatus paymentStatus;

    public Participant(Long participantId) {
        this.participantId = participantId;
    }

}
