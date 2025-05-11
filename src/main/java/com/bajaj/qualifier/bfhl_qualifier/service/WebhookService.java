package com.bajaj.qualifier.bfhl_qualifier.service;

import com.bajaj.qualifier.bfhl_qualifier.model.FinalQueryRequest;
import com.bajaj.qualifier.bfhl_qualifier.model.WebhookResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class WebhookService {

    private final RestTemplate restTemplate;

    public WebhookService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WebhookResponse generateWebhook() {
        String url = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";

        Map<String, String> body = Map.of(
                "Name", "Arpit Patel",
                "regNo", "0827CI221029",
                "email", "arpitpatel220829@acropolis.in"
        );

        return restTemplate.postForObject(url, body, WebhookResponse.class);
    }

    public void sendFinalQuery(String webhookUrl, String accessToken, String finalQuery) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken);

        FinalQueryRequest request = new FinalQueryRequest(finalQuery);
        HttpEntity<FinalQueryRequest> entity = new HttpEntity<>(request, headers);

        restTemplate.postForEntity(webhookUrl, entity, String.class);
    }
}
