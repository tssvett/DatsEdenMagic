package dev.team.visualization;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PixelVisualizer extends JPanel {
    private ArrayList<DrawableObject> objects;
    private final int scaleFactor = 2; // Масштаб увеличения

    public PixelVisualizer(int width, int height) {
        objects = new ArrayList<>();
        drawInitialObjects();

        // Добавление слушателя для отслеживания нажатий мыши
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Обновление позиции последнего объекта на координаты клика с учетом масштаба
                if (!objects.isEmpty()) {
                    DrawableObject lastObject = objects.get(objects.size() - 1);
                    lastObject.setPosition(e.getX() / scaleFactor, e.getY() / scaleFactor);
                    repaint(); // Перерисовка панели
                }
            }
        });
    }

    private void drawInitialObjects() {
        // Создание нескольких объектов с различными радиусами и цветами
        objects.add(new DrawableObject(100 / scaleFactor, 100 / scaleFactor, 20, Color.RED, 40, Color.BLUE));
        objects.add(new DrawableObject(200 / scaleFactor, 200 / scaleFactor, 30, Color.GREEN, 60, Color.YELLOW));
        objects.add(new DrawableObject(300 / scaleFactor, 300 / scaleFactor, 25, Color.MAGENTA, 50, Color.CYAN));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Установка серого фона
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Масштабирование графики
        g2d.scale(scaleFactor, scaleFactor); // Увеличение масштаба в 2 раза

        // Рисуем все объекты
        for (DrawableObject obj : objects) {
            obj.draw(g2d);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pixel Visualizer");
        PixelVisualizer panel = new PixelVisualizer(1000, 1000);
        frame.add(panel);
        frame.setSize(1000, 1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
