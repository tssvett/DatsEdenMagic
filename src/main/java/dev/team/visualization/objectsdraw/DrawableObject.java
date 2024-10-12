package dev.team.visualization.objectsdraw;

import java.awt.*;

public class DrawableObject {
    protected Circle innerCircle;
    protected Circle outerCircle;

    public DrawableObject(int x, int y, int innerRadius, Color innerColor, int outerRadius, Color outerColor) {
        this.innerCircle = new Circle(x, y, innerRadius, innerColor);
        this.outerCircle = new Circle(x, y, outerRadius, outerColor);
    }

    public void draw(Graphics g) {
        outerCircle.draw(g); // Draw outer circle
        innerCircle.draw(g);  // Draw inner circle

        // Draw color name above the inner circle
        g.setColor(Color.BLACK); // Text color
        String colorName = getColorName(innerCircle.getColor());
        g.drawString(colorName, innerCircle.getX() - 20, innerCircle.getY() - (innerCircle.getRadius() / 2) - 5);
    }

    protected String getColorName(Color color) {
        if (color.equals(Color.RED)) return "Red";
        if (color.equals(Color.GREEN)) return "Green";
        if (color.equals(Color.BLUE)) return "Blue";
        if (color.equals(Color.YELLOW)) return "Yellow";
        if (color.equals(Color.MAGENTA)) return "Magenta";
        if (color.equals(Color.CYAN)) return "Cyan";
        if (color.equals(Color.GRAY)) return "Gray";
        return "Unknown"; // For other colors
    }

    public void setPosition(int x, int y) {
        innerCircle = new Circle(x, y, innerCircle.getRadius(), innerCircle.getColor());
        outerCircle = new Circle(x, y, outerCircle.getRadius(), outerCircle.getColor());
    }

    // Getter methods for colors
    public Color getInnerColor() {
        return innerCircle.getColor();
    }

    public Color getOuterColor() {
        return outerCircle.getColor();
    }
}