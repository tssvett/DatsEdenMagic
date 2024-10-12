package dev.team.game;

import dev.team.dto.MoveResponse;
import dev.team.visualization.GameRenderer;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class GameCameraController extends JPanel implements KeyListener {
    private final GameRenderer renderer; // Reference to GameRenderer
    private final Set<Integer> pressedKeys = new HashSet<>(); // Track currently pressed keys
    private final Timer movementTimer; // Timer for continuous movement
    private MoveResponse moveResponse;

    public GameCameraController(GameRenderer renderer) {
        this.renderer = renderer; // Initialize the reference
        setFocusable(true); // Make sure it can receive focus
        addKeyListener(this); // Add the KeyListener

        // Initialize and start the timer for continuous movement
        movementTimer = new Timer(16, e -> updateCameraMovement()); // ~60 FPS
        movementTimer.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch (e.getKeyChar()) {
            case '=':
                increaseScale();
                break;
            case '-':
                decreaseScale();
                break;
            case '1':
                Integer x = moveResponse.transports().get(0).getX();
                Integer y = moveResponse.transports().get(0).getY();
                System.out.println(x + " " + y);
                moveCameraTo(x, y);
                break;
            case '2':
                moveCameraTo(moveResponse.transports().get(1).getX(), moveResponse.transports().get(1).getY());
                break;
            case '3':
                moveCameraTo(moveResponse.transports().get(2).getX(), moveResponse.transports().get(2).getY());
                break;
            case '4':
                moveCameraTo(moveResponse.transports().get(3).getX(), moveResponse.transports().get(3).getY());
                break;
            case '5':
                moveCameraTo(moveResponse.transports().get(4).getX(), moveResponse.transports().get(4).getY());
                break;
        }
    }
    public void getShips(MoveResponse moveResponse) {
        this.moveResponse = moveResponse;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        pressedKeys.add(e.getKeyCode()); // Add the key to the set of pressed keys
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys.remove(e.getKeyCode()); // Remove the key from the set of pressed keys
    }

    private void updateCameraMovement() {
        double deltaX = 0;
        double deltaY = 0;

        if (pressedKeys.contains(KeyEvent.VK_W)) {
            deltaY += 10; // Move up
        }
        if (pressedKeys.contains(KeyEvent.VK_S)) {
            deltaY -= 10; // Move down
        }
        if (pressedKeys.contains(KeyEvent.VK_A)) {
            deltaX += 10; // Move left
        }
        if (pressedKeys.contains(KeyEvent.VK_D)) {
            deltaX -= 10; // Move right
        }

        renderer.moveCameraBy(deltaX, deltaY); // Update camera position in renderer
    }

    public void moveCameraTo(double x, double y) {
        renderer.moveCameraTo(-x * renderer.getScaleFactor() + renderer.getWidth() / 2, -y * renderer.getScaleFactor() + renderer.getHeight() / 2);
    }

    private void increaseScale() {
        double newScale = Math.min(renderer.getScaleFactor() + 0.01, 2.0); // Limit max scale to 2.0
        renderer.setScaleFactor(newScale); // Update scale factor in renderer
        System.out.println("Scale increased: " + newScale);
    }

    private void decreaseScale() {
        double newScale = Math.max(renderer.getScaleFactor() - 0.01, 0.00005); // Limit min scale to 0.1
        renderer.setScaleFactor(newScale); // Update scale factor in renderer
        System.out.println("Scale decreased: " + newScale);
    }


}