package dev.team.game.workvector;

import dev.team.models.Vector2D;

public class Geometry {
    public double length(Vector2D vector2D) {
        return Math.sqrt(vector2D.x() * vector2D.x() + vector2D.y() * vector2D.y());
    }
}
