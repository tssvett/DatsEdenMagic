package dev.team.visualization;

import dev.team.visualization.objectsdraw.DrawableObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameRenderer extends JPanel {
    private List<DrawableObject> objects = new ArrayList<>();
    private final double SCALE_FACTOR = 0.5;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        setupBackground(g2d);

        scaleObjects(g2d);

        drawAllObjects(g2d);
    }


    public void draw(List<DrawableObject> updatedObjects) {
        this.objects = updatedObjects; // Обновление списка объектов
        repaint(); // Перерисовка после обновления объектов
    }

    private void drawAllObjects(Graphics2D g2d) {
        // Рисуем все объекты
        for (DrawableObject obj : objects) {
            obj.draw(g2d);
        }
    }

    private void scaleObjects(Graphics2D g2d) {
        // Масштабирование графики
        g2d.scale(SCALE_FACTOR, SCALE_FACTOR); // Увеличение масштаба в SCALE_FACTOR раз
    }

    private void setupBackground(Graphics2D g2d) {
        // Установка серого фона
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}
