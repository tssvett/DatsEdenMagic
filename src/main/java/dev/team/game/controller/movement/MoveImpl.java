package dev.team.game.controller.movement;

import dev.team.game.workvector.Coordinate;
import dev.team.models.Bounty;
import dev.team.models.TransportResponse;
import dev.team.models.Vector2D;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class MoveImpl implements Move {
    private static final double MAX_ACCELERATION = 10.0; // Максимальное ускорение (например, 10 м/с²)


    public Vector2D getMaxAccelerationToPointWithoutAnomaly(TransportResponse transport, Coordinate point) {
        Coordinate transportCoord = new Coordinate(transport.getX(), transport.getY());

        // 1. Вычисляем вектор AB
        Coordinate vectorAB = Coordinate.vectorBetween(transportCoord, point);

        // 2. Вычисляем длину вектора AB
        double lengthAB = Coordinate.distance(transportCoord, point);

        // 3. Ограничение на максимальное ускорение
        double maxAcceleration = MAX_ACCELERATION;

        // 4. Вычисляем коэффициент масштабирования
        double scalingFactor = maxAcceleration / lengthAB;

        // 5. Масштабируем вектор AB
        double scaledX = vectorAB.getX() * scalingFactor;
        double scaledY = vectorAB.getY() * scalingFactor;

        return new Vector2D(scaledX, scaledY);
    }

    public Vector2D getAccelerationToPoint(
            TransportResponse transportResponse, Coordinate point) {

        Vector2D speed = transportResponse.getVelocity();
        Vector2D accelerationAnomaly = transportResponse.getAnomalyAcceleration();

        Coordinate ship = new Coordinate(transportResponse.getX(), transportResponse.getY());
        // 1. Вычисляем вектор к цели
        Vector2D vectorToTarget = new Vector2D(
                point.getX() - ship.getX(),
                point.getY() - ship.getY()
        );

        // 2. Находим расстояние до цели
        double distance = Coordinate.distance(ship, point);

        // 3. Приблизительное время прибытия (если двигаться с текущей скоростью)
        double timeToReach = distance / speed.length();

        // 4. Вычисляем требуемое ускорение a1 по формуле
        double a1x = (2 * (vectorToTarget.x() - speed.x() * timeToReach)) / (timeToReach * timeToReach)
                - accelerationAnomaly.x();
        double a1y = (2 * (vectorToTarget.y() - speed.y() * timeToReach)) / (timeToReach * timeToReach)
                - accelerationAnomaly.y();

        Vector2D requiredAcceleration = new Vector2D(a1x, a1y);

        // 5. Ограничиваем ускорение до MAX_ACCELERATION, если нужно
        if (requiredAcceleration.length() > MAX_ACCELERATION) {
            double scale = MAX_ACCELERATION / requiredAcceleration.length();
            requiredAcceleration = requiredAcceleration.scale(scale);
        }

        return requiredAcceleration;
    }



}