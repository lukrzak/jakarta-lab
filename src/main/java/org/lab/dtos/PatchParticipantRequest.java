package org.lab.dtos;

import org.lab.models.PaymentStatus;

public class PatchParticipantRequest {

    private String email;
    private PaymentStatus paymentStatus;

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
