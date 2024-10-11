package dev.team.visualization;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class PixelVisualizer extends JPanel {
    private BufferedImage image;
    private int circleX = 50; // Начальная позиция круга по X
    private int circleY = 50; // Начальная позиция круга по Y
    private final int circleRadius = 50; // Радиус круга
    private final int scaleFactor = 2; // Масштаб увеличения

    public PixelVisualizer(int width, int height) {
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        drawPixels();

        // Добавление слушателя для отслеживания нажатий мыши
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Обновление позиции круга на координаты клика с учетом масштаба
                circleX = e.getX() / scaleFactor; // Делим на масштаб
                circleY = e.getY() / scaleFactor; // Делим на масштаб
                System.out.println("Circle moved to: (" + circleX + ", " + circleY + ")");
                repaint(); // Перерисовка панели
            }
        });
    }

    private void drawPixels() {
        // Установка цвета пикселей
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                if ((x & 1) == 1 || (y & 1) == 1) {
                    image.setRGB(x, y, Color.GRAY.getRGB());
                } else {
                    image.setRGB(x, y, Color.WHITE.getRGB()); // Остальные пиксели белые
                }
            }
        }
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

        g2d.drawImage(image, 0, 0, null);

        // Рисуем круг в новой позиции с учетом масштаба
        g2d.setColor(Color.BLUE);
        g2d.fillOval(circleX - circleRadius / 2, circleY - circleRadius / 2, circleRadius, circleRadius);
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
