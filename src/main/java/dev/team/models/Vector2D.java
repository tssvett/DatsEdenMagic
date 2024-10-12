package dev.team.models;

import dev.team.game.workvector.Coordinate;

public record Vector2D(Double x, Double y) {

    public Vector2D normalize() {
        double length = length();
        return new Vector2D(x / length, y / length);
    }

    public Vector2D scale(double scalar) {
        return new Vector2D(x * scalar, y * scalar);
    }

    public double length() {
        return Math.sqrt(x * x + y * y);
    }



}
