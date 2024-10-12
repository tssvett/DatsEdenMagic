package dev.team.visualization.objectsdraw.drawable.extend;

import dev.team.visualization.objectsdraw.drawable.DrawableObject;

import java.awt.*;

public class DrawableBounty extends DrawableObject {
    private static final Color innerColor = Color.YELLOW;
    private static final Color outerColor = null;

    private static final int innerRadius = 20;

    private final int moneyNominal;

    public DrawableBounty(int x, int y, int moneyNominal, int outerRadius) {
        super(x, y, innerRadius, innerColor, outerRadius, outerColor);
        this.moneyNominal = moneyNominal;
    }

    @Override
    public void draw(Graphics g) {

        g.setColor(getInnerColor()); // Use the inner circle's color
        innerCircle.draw(g); // Draw the inner circle

        g.setColor(new Color(38, 213, 71)); // Use the outer circle's color
        outerCircle.draw(g); // Draw the outer circle

        drawText(g);
    }

    private void drawText(Graphics g) {
        // Set font size and style for the money text
        Font originalFont = g.getFont(); // Save original font
        Font newFont = new Font(originalFont.getName(), Font.BOLD, 16); // Increase size and make it bold
        g.setFont(newFont);

        // Convert moneyNominal to a string
        String moneyText = String.valueOf(moneyNominal);

        // Calculate the width of the money text to center it
        FontMetrics metrics = g.getFontMetrics(newFont);
        int textWidth = metrics.stringWidth(moneyText);

        // Draw money text above the inner circle, centered horizontally
        g.setColor(Color.BLACK); // Text color
        int xPosition = innerCircle.getX() - (textWidth / 2); // Centering the text
        int yPosition = innerCircle.getY() - (innerCircle.getRadius() / 2) - 5; // Position above the circle
        g.drawString(moneyText, xPosition, yPosition);

        // Restore original font
        g.setFont(originalFont);
    }

}