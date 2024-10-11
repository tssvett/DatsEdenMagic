package dev.team;

import dev.team.config.WebClientCreator;
import dev.team.game.Game;
import dev.team.game.GameController;
import dev.team.integration.GameAPI;
import dev.team.visualization.GameRenderer;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@UtilityClass
public class Main {
    public static final String TOKEN = System.getenv().get("TOKEN");
    public static final String PROD_URL = "https://games.datsteam.dev";
    public static final String TEST_URL = "https://games-test.datsteam.dev";
    public static void main(String[] args) {
        WebClient webClient = new WebClientCreator(TEST_URL, TOKEN).webClient();

        GameAPI gameAPI = new GameAPI(webClient);
        GameController controller = new GameController();
        GameRenderer renderer = new GameRenderer();


        Game game = new Game(renderer, controller, gameAPI);
        game.run();
    }
}
