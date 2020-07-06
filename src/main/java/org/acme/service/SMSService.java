package org.acme.service;

import org.springframework.http.*;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.inject.Singleton;

@Singleton
public class SMSService {

    private String server = "https://api.eu-gb.apiconnect.appdomain.cloud/cardlink-possible/possible/api/v1/sms";
    private RestTemplate rest;
    private HttpHeaders headers;
    private HttpStatus status;

    public SMSService() {
        rest = new RestTemplate();
        headers = new HttpHeaders();
        headers.add("X-IBM-Client-Id",
                "02c0925c-44a2-4cc9-a36e-f596d8ff4ae0");
        headers.add("X-IBM-Client-Secret",
                "xU1jT1oO8nP3mV8aE3wT0wH5hK2pP4iL6qJ8qG5tX1fA4rT7mF");
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        headers.add("Accept", "*/*");
    }

    public String post(String mobile, String receipt) {
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("to", mobile);
        map.add("text", receipt);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(map, headers);
        ResponseEntity<String> responseEntity = rest.exchange(server, HttpMethod.POST, requestEntity, String.class);
        this.setStatus(responseEntity.getStatusCode());
        return responseEntity.getBody();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

}
