package dev.team;

import dev.team.config.WebClientCreator;
import dev.team.dto.MoveRequest;
import dev.team.dto.MoveResponse;
import dev.team.integration.GameAPI;
import dev.team.serialization.MoveResponseJsonService;
import org.springframework.web.reactive.function.client.WebClient;

public class TestMain {
    public static void main(String[] args) {
        String token = System.getenv().get("TOKEN");
        String prodUrl = "https://games.datsteam.dev";
        String testUrl = "https://games-test.datsteam.dev";


        WebClient webClient = new WebClientCreator(testUrl, token).webClient();

        GameAPI gameAPI = new GameAPI(webClient);

        MoveResponse moveResponse = gameAPI.sendMoveRequest();

        MoveResponseJsonService.saveToJson(moveResponse, "moveResponse20.json");
    }
}
