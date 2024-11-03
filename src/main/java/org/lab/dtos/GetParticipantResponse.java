package org.lab.dtos;

import org.lab.models.Participant;
import org.lab.models.PaymentStatus;

public class GetParticipantResponse {

    private Long id;
    private String email;
    private PaymentStatus paymentStatus;

    public GetParticipantResponse(Participant participant) {
        this.id = participant.getId();
        this.email = participant.getEmail();
        this.paymentStatus = participant.getPaymentStatus();
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
