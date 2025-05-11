package com.bajaj.qualifier.bfhl_qualifier;

import com.bajaj.qualifier.bfhl_qualifier.model.WebhookResponse;
import com.bajaj.qualifier.bfhl_qualifier.service.WebhookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartupRunner implements CommandLineRunner {

    private final WebhookService webhookService;

    public StartupRunner(WebhookService webhookService) {
        this.webhookService = webhookService;
    }

    @Override
    public void run(String... args) {
        WebhookResponse response = webhookService.generateWebhook();

        String finalQuery = "SELECT * FROM ... WHERE ...";

        webhookService.sendFinalQuery(response.getWebhookUrl(), response.getAccessToken(), finalQuery);
    }
}
