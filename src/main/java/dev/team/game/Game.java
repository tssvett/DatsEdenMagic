package dev.team.game;

import dev.team.dto.MoveResponse;
import dev.team.integration.GameAPI;
import dev.team.strategy.Strategy;
import dev.team.visualization.GameRenderer;
import dev.team.visualization.objectsdraw.ConvertToListDrawObjects;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.swing.*;

public class Game {
    private final GameRenderer renderer;
    private final GameCameraController controller;
    private final GameAPI gameAPI;
    private final Strategy strategy;
    private MoveResponse moveResponse;


    public Game(GameRenderer renderer, GameCameraController controller, GameAPI gameAPI, Strategy strategy) {
        this.renderer = renderer;
        this.controller = controller;
        this.gameAPI = gameAPI;
        this.strategy = strategy;

        moveResponse = gameAPI.sendMoveRequest();
    }


    public void run() {
        applyJFrame();
        Timer timer = new Timer(2000, e -> runLoop());
        timer.start();
    }

    private void runLoop() {
        //MoveResponse moveResponse = MoveResponseJsonService.loadFromJson(getNameFile());
        //MoveResponse moveResponse = MoveResponseJsonService.loadFromJson("moveResponse1.json");
        renderer.draw(ConvertToListDrawObjects.convertToListDrawObjects(moveResponse));

        //Strategy && Send request
        moveResponse = gameAPI.sendMoveRequest(strategy.makeStrategyStep(moveResponse));
    }

    private void applyJFrame() {
        JFrame frame = new JFrame("Game");
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        controller.requestFocusInWindow();


        frame.add(controller);
        frame.add(renderer);

    }
}
