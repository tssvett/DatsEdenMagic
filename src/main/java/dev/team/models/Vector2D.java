package dev.team.models;

public record Vector2D(double x, double y) {

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
