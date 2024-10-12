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

    private static final double MAX_SPEED = 110;

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

    public Vector2D getAccelerationToPointForSmallSpeed(
            TransportResponse transportResponse, Coordinate point) {

        Vector2D speed = transportResponse.getVelocity();
        Vector2D accelerationAnomaly = transportResponse.getAnomalyAcceleration();

        Coordinate ship = new Coordinate(transportResponse.getX(), transportResponse.getY());
        // 1. Вычисляем вектор к цели
        Vector2D vectorToTarget = new Vector2D(
                point.getX().doubleValue() - ship.getX(),
                point.getY().doubleValue() - ship.getY()
        );

        // 2. Находим расстояние до цели
        double distance = Coordinate.distance(ship, point);

        // 3. Приблизительное время прибытия (если двигаться с текущей скоростью)
        double timeToReach;
        if (speed.length() == 0){
            timeToReach = distance / MAX_SPEED;
        } else {
            timeToReach = distance / speed.length();
        }

        // 4. Вычисляем требуемое ускорение a1 по формуле
        double a1x = (2 * (vectorToTarget.x() - speed.x() * timeToReach)) / (timeToReach * timeToReach)
                - accelerationAnomaly.x();
        double a1y = (2 * (vectorToTarget.y() - speed.y() * timeToReach)) / (timeToReach * timeToReach)
                - accelerationAnomaly.y();

        Vector2D requiredAcceleration = new Vector2D(a1x, a1y);

        // 5. Ограничиваем ускорение до MAX_ACCELERATION, если нужно
        if (requiredAcceleration.length() < 50 || requiredAcceleration.length() >= MAX_ACCELERATION*9.9) {
            requiredAcceleration = requiredAcceleration.scale(MAX_ACCELERATION / requiredAcceleration.length());
        }

        return requiredAcceleration;
    }

    public Vector2D getAccelerationToPointForBigSpeed(
            TransportResponse transportResponse, Coordinate point) {

        Vector2D speed = transportResponse.getVelocity();
        Vector2D accelerationAnomaly = transportResponse.getAnomalyAcceleration();
        Coordinate ship = new Coordinate(transportResponse.getX(), transportResponse.getY());

        // 1. Вычисляем вектор к цели
        Vector2D vectorToTarget = new Vector2D(
                point.getX().doubleValue() - ship.getX(),
                point.getY().doubleValue() - ship.getY()
        );

        // 2. Находим расстояние до цели
        double distance = Coordinate.distance(ship, point);

        // 3. Приблизительное время прибытия (если двигаться с текущей скоростью)
        double timeToReach = (speed.length() == 0)
                ? distance / MAX_SPEED
                : distance / speed.length();

        // 4. Вычисляем требуемое ускорение a1 по формуле
        double a1x = (2 * (vectorToTarget.x() - speed.x() * timeToReach)) / (timeToReach * timeToReach)
                - accelerationAnomaly.x();
        double a1y = (2 * (vectorToTarget.y() - speed.y() * timeToReach)) / (timeToReach * timeToReach)
                - accelerationAnomaly.y();

        Vector2D requiredAcceleration = new Vector2D(a1x, a1y);

        // 5. Ограничиваем ускорение до MAX_ACCELERATION для плавности
        double accelerationMagnitude = requiredAcceleration.length();
        if (accelerationMagnitude > MAX_ACCELERATION) {
            requiredAcceleration = requiredAcceleration.scale(MAX_ACCELERATION / accelerationMagnitude);
        }

        // 6. Демпфирование для плавного движения (затухание ускорения)
        final double DAMPING_FACTOR = 0.5;  // Регулирует плавность
        requiredAcceleration = requiredAcceleration.scale(1 - DAMPING_FACTOR);

        // 7. Избегаем мелких изменений ускорения (например, менее 1)
        if (accelerationMagnitude < 5) {
            return new Vector2D(0.0, 0.0);  // Прекращаем корректировки
        }

        return requiredAcceleration;
    }


}