package dev.team.game.workvector;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@Getter
@Setter
public class Coordinate {
    private double x;
    private double y;

    // Метод для сложения координат
    public Coordinate add(Coordinate other) {
        return new Coordinate(this.x + other.x, this.y + other.y);
    }

    // Метод для вычитания координат
    public Coordinate subtract(Coordinate other) {
        return new Coordinate(this.x - other.x, this.y - other.y);
    }

    // Метод для вычисления вектора между двумя точками
    public static Coordinate vectorBetween(Coordinate a, Coordinate b) {
        return new Coordinate(b.x - a.x, b.y - a.y);
    }

    // Метод для вычисления расстояния между двумя точками
    public static double distance(Coordinate a, Coordinate b) {
        return Math.sqrt(Math.pow(b.x - a.x, 2) + Math.pow(b.y - a.y, 2));
    }
}