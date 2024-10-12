package dev.team.game.controller.movement;

import dev.team.game.workvector.Coordinate;
import dev.team.models.Anomaly;
import dev.team.models.Enemy;
import dev.team.models.TransportResponse;
import dev.team.models.Vector2D;

import java.util.ArrayList;
import java.util.List;

public class Evasion {
    TransportResponse transportResponse;
    List<Enemy> enemyList;
    List<Anomaly> anomalyList;

    public Evasion(TransportResponse transportResponse, List<Enemy> EnemyList, List<Anomaly> AnomalyList) {
        this.transportResponse = transportResponse;
        this.enemyList = EnemyList;
        this.anomalyList = AnomalyList;
    }

    private Vector2D getLifeline() {
        List<Vector2D> dangerVectors = findDangerVector();
        List <Vector2D> perpendicularVectors= new ArrayList<>();
        for (Vector2D vector : dangerVectors) {
            perpendicularVectors.add(vector.perpendicularTowards(Coordinate.toCoordinate(transportResponse)));
        }
        return Vector2D.sumVectors(perpendicularVectors);
    }

    private List<Vector2D> findDangerVector() {
        List<Vector2D> dangerVectors = new ArrayList<>();
        Coordinate coordinateTransport = new Coordinate(transportResponse.getX(), transportResponse.getY());
        Coordinate coordinateDanger;
        for (Enemy enemy : enemyList) {
            coordinateDanger = new Coordinate(enemy.getX(), enemy.getY());
            if (willIntersect(coordinateTransport, coordinateDanger, transportResponse.getVelocity(), enemy.getVelocity())) {
                dangerVectors.add(enemy.getVelocity());
            }
        }

        for (Anomaly anomaly : anomalyList) {
            coordinateDanger = new Coordinate(anomaly.getX(), anomaly.getY());
            if (willIntersect(coordinateTransport, coordinateDanger, transportResponse.getVelocity(), anomaly.getVelocity())) {
                dangerVectors.add(anomaly.getVelocity());
            }

        }
        return dangerVectors;
    }

    public static boolean willIntersect(Coordinate coordinate1, Coordinate coordinate2, Vector2D vector1, Vector2D vector2) {
        double deltaX = coordinate2.getX() - coordinate1.getX();
        double deltaY = coordinate2.getY() - coordinate1.getY();

        double a = vector1.x() - vector2.x();
        double b = vector1.y() - vector2.y();

        if (a == 0 && b == 0) {
            // Вектора параллельны и не пересекаются
            return false;
        }

        if (a == 0) { // v1x == v2x
            return deltaX == 0 && ((deltaY / b) >= 0); // проверка по y
        }

        if (b == 0) { // v1y == v2y
            return deltaY == 0 && ((deltaX / a) >= 0); // проверка по x
        }

        double tX = deltaX / a; // Время во втором уравнении
        double tY = deltaY / b; // Время в первом уравнении

        return tX == tY && tX >= 0; // Пересекаются в одном времени и t >= 0
    }

}
