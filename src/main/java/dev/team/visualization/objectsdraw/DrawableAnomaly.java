package dev.team.visualization.objectsdraw;

import java.awt.*;

public class DrawableAnomaly extends DrawableObject {
    private static final Color innerColor = new Color(0, 255, 0, 128);
    private static final Color outerColor = new Color(0, 128, 0, 128);

    public DrawableAnomaly(int x, int y, int innerRadius, int outerRadius) {
        super(x, y, innerRadius, innerColor, outerRadius, outerColor);
    }

    @Override
    public void draw(Graphics g) {
        // Create a transparent version of the outer circle's color
        Color transparentOuterColor = new Color(getOuterColor().getRed(),
                getOuterColor().getGreen(),
                getOuterColor().getBlue(),
                128); // Adjust alpha value as needed

        g.setColor(transparentOuterColor);
        outerCircle.draw(g); // Draw the outer circle

        g.setColor(getInnerColor()); // Use the inner circle's color
        innerCircle.draw(g); // Draw the inner circle

        // Draw color name above the inner circle
        g.setColor(Color.BLACK); // Text color
        String colorName = getColorName(innerCircle.getColor());
        g.drawString(colorName,
                innerCircle.getX() - 20,
                innerCircle.getY() - (innerCircle.getRadius() / 2) - 5);
    }
}