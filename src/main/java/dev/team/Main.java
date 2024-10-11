package dev.team;

import dev.team.config.WebClientCreator;
import dev.team.dto.MoveResponse;
import dev.team.integration.GameAPI;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@UtilityClass
public class Main {
    public static void main(String[] args) {
        String token = System.getenv().get("TOKEN");
        String prodUrl = "https://games.datsteam.dev";
        String testUrl = "https://games-test.datsteam.dev";


        WebClient webClient = new WebClientCreator(testUrl, token).webClient();

        GameAPI gameAPI = new GameAPI(webClient);

        MoveResponse moveResponse = gameAPI.sendMoveRequest();

        //logic



        log.info("{}", moveResponse);
        //log.info("{}", gameAPI.sendRoundRequest());


    }
}
