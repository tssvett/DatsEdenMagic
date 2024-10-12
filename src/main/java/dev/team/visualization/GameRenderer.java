package dev.team.visualization;

import dev.team.visualization.objectsdraw.drawable.DrawableObject;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameRenderer extends JPanel {
    private List<DrawableObject> objects = new ArrayList<>();

    // Getter for scale factor
    @Getter
    private double scaleFactor = 0.5; // Default scale factor

    // Camera position variables
    private double cameraX = 0;
    private double cameraY = 0;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        setupBackground(g2d);

        // Translate graphics context to simulate camera movement
        g2d.translate(cameraX, cameraY);

        scaleObjects(g2d);
        drawAllObjects(g2d);
    }

    public void draw(List<DrawableObject> updatedObjects) {
        this.objects = updatedObjects; // Update object list
        repaint(); // Repaint after updating objects
    }

    private void drawAllObjects(Graphics2D g2d) {
        for (DrawableObject obj : objects) {
            obj.draw(g2d);
        }
    }

    private void scaleObjects(Graphics2D g2d) {
        g2d.scale(scaleFactor, scaleFactor); // Apply scaling to graphics context
    }

    private void setupBackground(Graphics2D g2d) {
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    public void setScaleFactor(double newScaleFactor) {
        this.scaleFactor = newScaleFactor;
        repaint(); // Repaint after changing scale factor
    }

    // Method to move the camera
    public void moveCamera(double deltaX, double deltaY) {
        this.cameraX += deltaX;
        this.cameraY += deltaY;
        repaint(); // Repaint after moving the camera
    }
}