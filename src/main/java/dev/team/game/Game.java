package dev.team.game;

import dev.team.dto.MoveResponse;
import dev.team.integration.GameAPI;
import dev.team.serialization.MoveResponseJsonService;
import dev.team.visualization.GameRenderer;
import dev.team.visualization.objectsdraw.ConvertToListDrawObjects;
import lombok.RequiredArgsConstructor;

import javax.swing.*;

import static dev.team.utils.FileUtils.getNameFile;

@RequiredArgsConstructor
public class Game {
    private final GameRenderer renderer;
    private final GameCameraController controller;
    private final GameAPI gameAPI;


    public void run() {
        applyJFrame();
        Timer timer = new Timer(2000, e -> runLoop());
        timer.start();
    }

    private void runLoop() {
        //MoveResponse moveResponse = gameAPI.sendMoveRequest();
        //MoveResponse moveResponse = MoveResponseJsonService.loadFromJson(getNameFile());
        MoveResponse moveResponse = MoveResponseJsonService.loadFromJson("moveResponse1.json");
        renderer.draw(ConvertToListDrawObjects.convertToListDrawObjects(moveResponse));

        //Logic
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
