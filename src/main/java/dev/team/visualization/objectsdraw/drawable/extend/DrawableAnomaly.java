package dev.team.visualization.objectsdraw.drawable.extend;

import dev.team.visualization.objectsdraw.drawable.DrawableObject;

import java.awt.*;

public class DrawableAnomaly extends DrawableObject {
    private static final Color attractsAnomalyOuterColor = new Color(26, 9, 57, 128);
    private static final Color notAttractsAnomalyOuterColor = new Color(19, 198, 237, 128);

    private Double strength;

    public DrawableAnomaly(int x, int y, int innerRadius, int outerRadius, Double strength) {
        super(x, y, innerRadius, Color.black, outerRadius, strength > 0 ? attractsAnomalyOuterColor : notAttractsAnomalyOuterColor);
        this.strength = strength;
    }

    @Override
    public void draw(Graphics g) {


        if (strength > 0) {
            String text = "Притягивает с силой: " + strength;
            drawAnomaly(g, attractsAnomalyOuterColor, text);
        } else {
            String text = "Отталкивает с силой: " + strength;
            drawAnomaly(g, notAttractsAnomalyOuterColor, text);
        }

    }

    private void drawAnomaly(Graphics g, Color outerColor, String text) {
        g.setColor(outerColor);
        outerCircle.draw(g); // Draw the outer circle

        g.setColor(getInnerColor()); // Use the inner circle's color
        innerCircle.draw(g); // Draw the inner circle

        // Set font size and style for the text
        Font originalFont = g.getFont(); // Save original font
        Font newFont = new Font(originalFont.getName(), Font.BOLD, 16); // Increase size and make it bold
        g.setFont(newFont);

        // Calculate the width of the text to center it
        FontMetrics metrics = g.getFontMetrics(newFont);
        int textWidth = metrics.stringWidth(text);

        // Draw color name above the inner circle, centered horizontally
        g.setColor(Color.BLACK); // Text color
        int xPosition = innerCircle.getX() - (textWidth / 2); // Centering the text
        int yPosition = innerCircle.getY() - (innerCircle.getRadius() / 2) - 5; // Position above the circle
        g.drawString(text, xPosition, yPosition);

        // Restore original font
        g.setFont(originalFont);
    }
}