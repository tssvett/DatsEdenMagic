package dev.team.config;

import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
public class WebClientCreator {
    private final String url;
    private final String token;

    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(url)
                .defaultHeaders(headers -> {
                    headers.add("Accept", "application/json");
                    headers.add("X-Auth-Token", token);
                })
                .build();
    }
}
