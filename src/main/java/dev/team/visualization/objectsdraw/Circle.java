package dev.team.visualization.objectsdraw;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;


@Getter
@Setter
@AllArgsConstructor
public class Circle {
    private int x;
    private int y;
    private int radius;
    private Color color;

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x - radius / 2, y - radius / 2, radius, radius);
    }
}
