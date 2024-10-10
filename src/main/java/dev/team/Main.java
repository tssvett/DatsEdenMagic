package dev.team;

import dev.team.config.WebClientCreator;
import dev.team.integration.GameAPI;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@UtilityClass
public class Main {
    public static void main(String[] args) {
        String token = System.getenv().get("TOKEN");
        String url = "hhtp";
        WebClient webClient = new WebClientCreator(url, token).webClient();

        GameAPI gameAPI = new GameAPI(webClient);


    }
}
