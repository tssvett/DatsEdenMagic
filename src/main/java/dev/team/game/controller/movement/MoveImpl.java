package dev.team.game.controller.movement;

import dev.team.game.workvector.Coordinate;
import dev.team.models.TransportResponse;
import dev.team.models.Vector2D;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MoveImpl implements Move {
    private static final double MAX_ACCELERATION = 10.0; // Максимальное ускорение (например, 10 м/с²)

    /**
     * @param myShip         Выбранный кораблик
     * @param targetPosition Координаты точки, куда надо плыть
     * @return Вектор ускорения
     */
    @Override
    public Vector2D getAccelerationToPoint(TransportResponse myShip, Coordinate point, Vector2D anomalyAcceleration) {
        // Текущие координаты ковра
        Vector2D currentPosition = new Vector2D(myShip.getX().doubleValue(), myShip.getY().doubleValue());

        // Вычисляем вектор направления
        double dirX = targetPosition.x() - currentPosition.x();
        double dirY = targetPosition.y() - currentPosition.y();

        // Длина вектора направления
        double length = Math.sqrt(dirX * dirX + dirY * dirY);

        // Если длина больше нуля, нормализуем и масштабируем
        if (length > 0) {
            // Нормализация
            double normX = dirX / length;
            double normY = dirY / length;


        }

        // Если длина равна нулю, возвращаем нулевое ускорение
        return new Vector2D(0.0, 0.0);
    }

    public Vector2D getAccelerationToPointWithoutAnomaly(TransportResponse transport, Coordinate point) {
        Coordinate transportCoord = new Coordinate(transport.getX(), transport.getY());

        // 1. Вычисляем вектор AB
        Coordinate vectorAB = Coordinate.vectorBetween(transportCoord, point);

        // 2. Вычисляем длину вектора AB
        double lengthAB = Coordinate.distance(transportCoord, point);

        // 3. Ограничение на максимальное ускорение
        double maxAcceleration = MAX_ACCELERATION;

        // 4. Если длина вектора меньше или равна максимальному ускорению, возвращаем его
        if (lengthAB <= maxAcceleration) {
            return new Vector2D(vectorAB.getX(), vectorAB.getY());
        }

        // 5. Вычисляем коэффициент масштабирования
        double scalingFactor = maxAcceleration / lengthAB;

        // 6. Масштабируем вектор AB
        double scaledX = vectorAB.getX() * scalingFactor;
        double scaledY = vectorAB.getY() * scalingFactor;

        return new Vector2D(scaledX, scaledY);
    }

//    public Vector2D getAccelerationToNearestBounty(TransportResponse myShip, List<Bounty> bountyList) {
//        if (bountyList == null || bountyList.isEmpty()) {
//            log.info("Корабль {} не может плыть к монеткам потому что их нет", myShip.getId());
//            return null;
//        }
//
//        //Поиск ближайшей монетки для данного корабля
//
//        Bounty nearestBounty = null;
//        double minDistance = Double.MAX_VALUE;
//        for (Bounty bounty : bountyList) {
//            double distance = calculateDistance(myShip.getX(), myShip.getY(), bounty.getX(), bounty.getY());
//            if (distance < minDistance) {
//                minDistance = distance;
//                nearestBounty = bounty;
//            }
//        }
//        return getAccelerationToPoint(myShip, new Vector2D(nearestBounty.getX().doubleValue(), nearestBounty.getY().doubleValue()));
//
//
//    }

    private double calculateDistance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}