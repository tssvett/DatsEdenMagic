package dev.team.visualization.objectsdraw.drawable.extend;

import dev.team.models.Vector2D;
import dev.team.visualization.objectsdraw.drawable.DrawableObject;

import java.awt.*;

public class DrawableAlly extends DrawableObject {

    private static final Color innerColor = Color.BLUE;
    private static final Color outerColor = null;
    private static final int allyShipSize = 20;
    private static final int outerRadius = 0;

    private int HP;
    private Vector2D velocity;

    public DrawableAlly(int x, int y, int HP, Vector2D velocity) {
        super(x, y, allyShipSize, innerColor, outerRadius, outerColor);
        this.HP = HP;
        this.velocity = velocity;
    }

    @Override
    public void draw(Graphics g) {
        drawHP(g);
        drawBody(g);
        drawVelocityArrow(g);
    }

    private void drawBody(Graphics g) {
        g.setColor(getInnerColor());
        innerCircle.draw(g);
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

    private void drawVelocityArrow(Graphics g) {
        // Cast Graphics to Graphics2D for better control
        Graphics2D g2d = (Graphics2D) g;

        // Set the stroke width for the arrow
        g2d.setStroke(new BasicStroke(3)); // Change the number to adjust thickness

        // Get center position of the enemy
        int centerX = innerCircle.getX();
        int centerY = innerCircle.getY();

        // Calculate arrow length based on velocity magnitude
        double velocityMagnitude = velocity.magnitude();
        double arrowLength = Math.min(velocityMagnitude * 2, 50); // Scale and limit length

        // Calculate angle from velocity vector
        double angle = Math.atan2(velocity.y(), velocity.x()); // Use y first for atan2

        // Calculate end point of the arrow
        int endX = (int) (centerX + arrowLength * Math.cos(angle));
        int endY = (int) (centerY + arrowLength * Math.sin(angle));

        // Draw line for arrow shaft
        g2d.setColor(Color.BLACK); // Set arrow color to black
        g2d.drawLine(centerX, centerY, endX, endY); // Draw the shaft of the arrow

        // Draw arrowhead (two lines)
        double arrowHeadAngle = Math.toRadians(30); // Angle for arrowhead lines
        int arrowHeadLength = 10; // Length of each arrowhead line

        int arrowEndX1 = (int) (endX - arrowHeadLength * Math.cos(angle - arrowHeadAngle));
        int arrowEndY1 = (int) (endY - arrowHeadLength * Math.sin(angle - arrowHeadAngle));

        int arrowEndX2 = (int) (endX - arrowHeadLength * Math.cos(angle + arrowHeadAngle));
        int arrowEndY2 = (int) (endY - arrowHeadLength * Math.sin(angle + arrowHeadAngle));

        g2d.drawLine(endX, endY, arrowEndX1, arrowEndY1); // First side of the arrowhead
        g2d.drawLine(endX, endY, arrowEndX2, arrowEndY2); // Second side of the arrowhead

        // Reset stroke to default if necessary
        g2d.setStroke(new BasicStroke(1)); // Resetting stroke to default thickness
    }
}
