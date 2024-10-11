package dev.team.visualization;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameRenderer extends JPanel {
    private ArrayList<DrawableObject> objects;
    private final int SCALE_FACTOR = 2;

    public GameRenderer() {
        objects = new ArrayList<>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Установка серого фона
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Масштабирование графики
        g2d.scale(SCALE_FACTOR, SCALE_FACTOR); // Увеличение масштаба в 2 раза

        // Рисуем все объекты
        for (DrawableObject obj : objects) {
            obj.draw(g2d);
        }


    }

    public void addDrawableObject(DrawableObject object) {
        objects.add(object); // Добавление объекта в список
        repaint(); // Перерисовка после добавления нового объекта
    }

    public void updateObjects(ArrayList<DrawableObject> updatedObjects) {
        this.objects = updatedObjects; // Обновление списка объектов
        repaint(); // Перерисовка после обновления объектов
    }

    public int getScaleFactor() {
        return SCALE_FACTOR;
    }

    public void draw() {

        repaint();


    }
}
