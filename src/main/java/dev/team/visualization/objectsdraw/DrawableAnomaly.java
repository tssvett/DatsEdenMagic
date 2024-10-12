package dev.team.visualization.objectsdraw;

import java.awt.*;

public class DrawableAnomaly extends DrawableObject {
    public DrawableAnomaly(int x, int y, int innerRadius, Color innerColor, int outerRadius, Color outerColor) {
        super(x, y, innerRadius, innerColor, outerRadius, outerColor);
    }

    @Override
    public void draw(Graphics g) {
        Color transparentOuterColor = new Color(outerCircle.getColor().getRed(),
                outerCircle.getColor().getGreen(),
                outerCircle.getColor().getBlue(),
                128); // Adjust alpha value (0-255) as needed

        Color transparentInnerColor = new Color(outerCircle.getColor().getRed(),
                outerCircle.getColor().getGreen(),
                outerCircle.getColor().getBlue(),
                128); // Adjust alpha value (0-255) as needed

        g.setColor(Color.magenta);
        outerCircle.draw(g); // Draw the outer circle

        g.setColor(Color.magenta); // Use inner circle's color
        innerCircle.draw(g); // Draw the inner circle

        // Рисуем название цвета над внутренним кругом
        g.setColor(Color.BLACK); // Цвет текста
        String colorName = getColorName(innerCircle.getColor());
        g.drawString(colorName, innerCircle.getX() - 20, innerCircle.getY() - (innerCircle.getRadius() / 2) - 5);
    }
}
