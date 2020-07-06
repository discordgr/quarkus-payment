package org.acme.entity;

public class PaymentResponse {

    private String status, message;

    public PaymentResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public PaymentResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
