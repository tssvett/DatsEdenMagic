package dev.team.game.defence;

import dev.team.models.Enemy;
import dev.team.models.TransportResponse;

import java.util.List;

public class ShieldImpl implements Shield {
    private static final double ATTACK_RANGE = 200.0; // Радиус поражения
    private static final double ATTACK_RADIUS = 20.0;  // Радиус поражения

    /**
     * @param myShip Выбранный кораблик
     * @param enemiesList Список врагов, которых мы видим
     * @return Флажок, надо ли активировать щит
     */
    @Override
    public Boolean isNeedToActivateShield(TransportResponse myShip, List<Enemy> enemiesList) {
        if (enemiesList == null || enemiesList.isEmpty()) {
            return false; // Нет врагов для проверки
        }

        for (Enemy enemy : enemiesList) {
            // Вычисляем расстояние до врага
            double distance = calculateDistance(myShip.getX(), myShip.getY(), enemy.getX(), enemy.getY());

            // Проверяем, находится ли враг в зоне поражения
            if (distance <= ATTACK_RANGE + ATTACK_RADIUS  && "alive".equals(enemy.getStatus())) {
                return true; // Щит нужно активировать
            }
        }

        return false; // Нет врагов в зоне поражения
    }

    // Метод для вычисления расстояния между двумя точками
    private double calculateDistance(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}