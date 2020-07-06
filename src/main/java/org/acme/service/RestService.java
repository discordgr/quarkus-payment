package org.acme.service;


import org.acme.entity.PaymentRequest;
import org.acme.entity.PaymentResponse;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RestService {

    @Inject
    EmailService emailService;

    @Inject
    SMSService smsService;

    public PaymentResponse processPayment(PaymentRequest paymentRequest) {
        PaymentResponse paymentResponse = new PaymentResponse();

        if ( paymentRequest.getEmail() != null ){
            emailService.sendEmail(paymentRequest.getEmail(),paymentRequest.getReceipt());
            paymentResponse.setMessage("Email Sent Successfully.");
        }else{
            paymentResponse.setMessage("Email address was false!");
        }

        if ( paymentRequest.getMobile() != null ){
            smsService.post(paymentRequest.getMobile(), paymentRequest.getReceipt());
            paymentResponse.setMessage(paymentResponse.getMessage() + " SMS sent successfully");
        }else{
            paymentResponse.setMessage("Receipt on SMS was not sent!");
        }

        return paymentResponse;
    }

}
