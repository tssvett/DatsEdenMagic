package dev.team.visualization.objectsdraw.drawable.extend;

import dev.team.visualization.objectsdraw.drawable.DrawableObject;

import java.awt.*;

public class DrawableEnemy extends DrawableObject {

    private static final Color innerColor = Color.RED;
    private static final Color outerColor = null;
    private static final int enemySizeInPixels = 20;
    private static final int outerRadius = 0;

    private int HP;

    public DrawableEnemy(int x, int y, int HP) {
        super(x, y, enemySizeInPixels, innerColor, outerRadius, outerColor);
        this.HP = HP;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getInnerColor()); // Use the inner circle's color
        innerCircle.draw(g); // Draw the inner circle

        drawHP(g);
    }

    private void drawHP(Graphics g) {
        // Set font size and style for the HP text
        Font originalFont = g.getFont(); // Save original font
        Font newFont = new Font(originalFont.getName(), Font.BOLD, 16); // Increase size and make it bold
        g.setFont(newFont);

        // Calculate the width of the HP text to center it
        String hpText = String.valueOf(HP);
        FontMetrics metrics = g.getFontMetrics(newFont);
        int textWidth = metrics.stringWidth(hpText);

        // Draw HP above the inner circle, centered horizontally
        g.setColor(Color.BLACK); // Text color
        int xPosition = innerCircle.getX() - (textWidth / 2); // Centering the text
        int yPosition = innerCircle.getY() - (innerCircle.getRadius() / 2) - 5; // Position above the circle
        g.drawString(hpText, xPosition, yPosition);

        // Restore original font
        g.setFont(originalFont);
    }
}
