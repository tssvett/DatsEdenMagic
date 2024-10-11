package dev.team.game;

import dev.team.config.WebClientCreator;
import dev.team.dto.MoveResponse;
import dev.team.integration.GameAPI;
import org.springframework.web.reactive.function.client.WebClient;

public class GameContoller {
    static MoveResponse start(){
        String token = System.getenv().get("TOKEN");
        String prodUrl = "https://games.datsteam.dev";
        String testUrl = "https://games-test.datsteam.dev";


        WebClient webClient = new WebClientCreator(testUrl, token).webClient();

        GameAPI gameAPI = new GameAPI(webClient);

        MoveResponse moveResponse = gameAPI.sendMoveRequest();

        return moveResponse;

    }
}
