package dev.team.visualization.objectsdraw;

import java.awt.*;

public enum TypeDrawObject {
    REPULSIVE_ANOMALY(Color.BLACK), // Отталкивающая аномалия - черный цвет
    ATTRACTIVE_ANOMALY(Color.GREEN);

    private final Color color;

    TypeDrawObject(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
