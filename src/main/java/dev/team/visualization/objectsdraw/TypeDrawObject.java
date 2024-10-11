package dev.team.visualization.objectsdraw;

import java.awt.*;

public enum TypeDrawObject {
    ALLY(Color.BLUE),            // Союзник - синий цвет
    ENEMY(Color.RED),            // Враг - красный цвет
    REPULSIVE_ANOMALY(Color.BLACK), // Отталкивающая аномалия - черный цвет
    ATTRACTIVE_ANOMALY(Color.GREEN),
    BOUNTY(Color.YELLOW); // Притягивающая аномалия - желтый цвет

    private final Color color;

    TypeDrawObject(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
