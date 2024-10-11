package dev.team;
import dev.team.config.WebClientCreator;
import dev.team.game.GameLoop;
import dev.team.integration.GameAPI;
import dev.team.visualization.GameRenderer;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import javax.swing.*;

@Slf4j
@UtilityClass
public class Main {
    public static void main(String[] args) {
        String token = System.getenv().get("TOKEN");
        String prodUrl = "https://games.datsteam.dev";
        String testUrl = "https://games-test.datsteam.dev";


        WebClient webClient = new WebClientCreator(testUrl, token).webClient();

        GameAPI gameAPI = new GameAPI(webClient);



        //log.info("{}", moveResponse);
        //log.info("{}", gameAPI.sendRoundRequest());


        JFrame frame = new JFrame("Game");
        GameRenderer renderer = new GameRenderer();
        GameLoop gameLoop = new GameLoop(renderer,gameAPI);
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}
