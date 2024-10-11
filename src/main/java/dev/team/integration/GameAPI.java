package dev.team.integration;

import dev.team.dto.MoveRequest;
import dev.team.dto.MoveResponse;
import dev.team.models.ErrorDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
public class GameAPI {
    private final WebClient webClient;


    public MoveResponse sendMoveRequest(MoveRequest moveRequest) {
        return webClient
                .post()
                .uri("/play/magcarp/player/move")
                .bodyValue(moveRequest)
                .retrieve()
                .onStatus(HttpStatusCode::isError, clientResponse -> {
                    log.error("Error response: {}", clientResponse);
                    return Mono.error(new RuntimeException(clientResponse.toString()));
                })
                .bodyToMono(new ParameterizedTypeReference<MoveResponse>() {
                })
                .doOnNext(categories -> log.info("Successfully get response"))
                .doOnError(e -> log.error("Error fetching categories: {}", e.getMessage()))
                .block();
    }

    public MoveResponse sendMoveRequest() {
        return webClient
                .post()
                .uri("/play/magcarp/player/move")
                .retrieve()
                .onStatus(HttpStatusCode::isError, clientResponse -> {
                    // Получаем тип контента
                    String contentType = clientResponse.headers().contentType().map(MediaType::toString).orElse("unknown");

                    // Логируем ошибку
                    log.error("Error response: {}", clientResponse);

                    // Обрабатываем текстовый ответ
                    if (contentType.equals("text/plain;charset=utf-8")) {
                        return clientResponse.bodyToMono(String.class)
                                .flatMap(errorMessage -> {
                                    log.error("Received error message: {}", errorMessage);
                                    return Mono.error(new RuntimeException(errorMessage));
                                });
                    } else {
                        // Если тип контента не текстовый, пытаемся десериализовать в ErrorDetails
                        return clientResponse.bodyToMono(ErrorDetails.class)
                                .flatMap(errorDetails -> {
                                    log.error("Error details: {}", errorDetails);
                                    return Mono.error(new RuntimeException(errorDetails.error()));
                                });
                    }
                })
                .bodyToMono(new ParameterizedTypeReference<MoveResponse>() {})
                .doOnNext(response -> log.info("Successfully got response"))
                .doOnError(e -> log.error("Error fetching response: {}", e.getMessage()))
                .block();
    }

}
