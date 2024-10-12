package dev.team.game;

import dev.team.dto.MoveResponse;
import dev.team.integration.GameAPI;
import dev.team.strategy.Strategy;
import dev.team.visualization.GameRenderer;
import dev.team.visualization.GeneralInfoOverlay;
import dev.team.visualization.MyShipsInfoOverlay;
import dev.team.visualization.objectsdraw.ConvertToListDrawObjects;

import javax.swing.*;

public class Game {
    private final GameRenderer renderer;
    private final GameCameraController controller;
    private final GameAPI gameAPI;
    private final Strategy strategy;
    private MoveResponse moveResponse;
    private GeneralInfoOverlay infoOverlay; // Declare the overlay
    private MyShipsInfoOverlay myShipsInfoOverlay; // Declare the overlay

    public Game(GameRenderer renderer, GameCameraController controller, GameAPI gameAPI, Strategy strategy) {
        this.renderer = renderer;
        this.controller = controller;
        this.gameAPI = gameAPI;
        this.strategy = strategy;

        moveResponse = gameAPI.sendMoveRequest();
    }

    public void run() {
        applyJFrame();
        Timer timer = new Timer(350, e -> runLoop());
        timer.start();
    }

    private void runLoop() {
        renderer.draw(ConvertToListDrawObjects.convertToListDrawObjects(moveResponse));
        // Update overlay information if needed
        infoOverlay.updateGameInfoLabel(moveResponse, renderer); // Example update
        myShipsInfoOverlay.updateGameInfoLabel(moveResponse);
        controller.getShips(moveResponse);

        moveResponse = gameAPI.sendMoveRequest(strategy.makeStrategyStep(moveResponse));
    }

    private void applyJFrame() {
        JFrame frame = new JFrame("Game");
        frame.setSize(1600, 1000);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Use JLayeredPane to manage layers
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(frame.getSize());

        // Add components to the layered pane
        renderer.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        controller.setBounds(0, 0, frame.getWidth(), frame.getHeight());


        layeredPane.add(controller, Integer.valueOf(1)); // Add controller at layer 2 (on top of renderer)
        layeredPane.add(renderer, Integer.valueOf(2)); // Add renderer at layer 1

        infoOverlay = new GeneralInfoOverlay(renderer); // Initialize the overlay
        infoOverlay.setBounds(0, 0, frame.getWidth(), frame.getHeight()); // Set size and position
        layeredPane.add(infoOverlay, Integer.valueOf(3)); // Add overlay at layer 3 (on top)

        myShipsInfoOverlay = new MyShipsInfoOverlay(); // Initialize the overlay
        myShipsInfoOverlay.setBounds(0, 0, frame.getWidth(), frame.getHeight()); // Set size and position
        layeredPane.add(myShipsInfoOverlay, Integer.valueOf(4)); // Add overlay at layer 3 (on top)

        frame.add(layeredPane); // Add layered pane to frame
        frame.setVisible(true);

        controller.requestFocusInWindow();
    }
}