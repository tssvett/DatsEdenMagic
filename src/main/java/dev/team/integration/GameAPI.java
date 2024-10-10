package dev.team.integration;

import dev.team.models.CarpetAirplane;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class GameAPI {
    private final WebClient webClient;


    public List<CarpetAirplane> getCarpetAirplane() {
        return webClient
                .get()
                .uri("/hesus/sos")
                .retrieve()
                .onStatus(HttpStatusCode::isError, clientResponse -> {
                    log.error("Error response: {}", clientResponse.statusCode());
                    return Mono.error(new RuntimeException("some error"));
                })
                .bodyToMono(new ParameterizedTypeReference<List<CarpetAirplane>>() {
                })
                .doOnNext(categories -> log.info("Successfully fetched {} categories", categories.size()))
                .doOnError(e -> log.error("Error fetching categories: {}", e.getMessage()))
                .block();
    }

}
