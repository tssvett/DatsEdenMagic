package dev.team.game.movement;

import dev.team.models.TransportResponse;
import dev.team.models.Vector2D;

public class MoveImpl implements Move {
    private static final double MAX_ACCELERATION = 10.0; // Максимальное ускорение (например, 10 м/с²)

    /**
     * @param myShip         Выбранный кораблик
     * @param targetPosition Координаты точки, куда надо плыть
     * @return Вектор ускорения
     */
    @Override
    public Vector2D getAccelerationToPoint(TransportResponse myShip, Vector2D targetPosition) {
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

            // Масштабируем до максимального ускорения
            return new Vector2D(normX * MAX_ACCELERATION, normY * MAX_ACCELERATION);
        }

        // Если длина равна нулю, возвращаем нулевое ускорение
        return new Vector2D(0.0, 0.0);
    }
}