package org.acme.controller;

import org.acme.entity.PaymentRequest;
import org.acme.entity.PaymentResponse;
import org.acme.service.RestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/payment")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RestController {

    @Inject
    RestService restService;

    @POST
    public ResponseEntity<PaymentResponse> pay(PaymentRequest paymentRequest) {
        if ( paymentRequest != null ){
            return new ResponseEntity<>(restService.processPayment(paymentRequest), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(new PaymentResponse("400","Null Input"), HttpStatus.BAD_REQUEST);
        }
    }
}