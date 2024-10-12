package dev.team.game.controller.attack.enemychoose;

import dev.team.game.workvector.Coordinate;
import dev.team.models.Enemy;
import dev.team.models.TransportResponse;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.AbstractMap;

public class EnemyList {
    private TransportResponse transportResponse;

    @Getter
    private List<Map.Entry<Enemy, Double>> enemyDistanceList;

    public EnemyList(TransportResponse transportResponse, List<Enemy> enemyList) {
        this.enemyDistanceList = new ArrayList<>();
        this.transportResponse = transportResponse;
        for (Enemy enemy : enemyList) {
            Coordinate coordinateTransport = new Coordinate(transportResponse.getX(), transportResponse.getY());
            Coordinate coordinateEnemy = new Coordinate(enemy.getX(), enemy.getY());
            addEnemyDistance(enemy, Coordinate.distance(coordinateTransport, coordinateEnemy));
        }

        // Сортировка по health
        Collections.sort(enemyDistanceList, Comparator.comparingInt(entry -> entry.getKey().getHealth()));
    }

    // Метод для добавления новой пары
    public void addEnemyDistance(Enemy enemy, double distance) {
        enemyDistanceList.add(new AbstractMap.SimpleEntry<>(enemy, distance));
    }


    public Enemy getMinHealthEnemyWithinDistance(double maxDistance) {
        return enemyDistanceList.stream()
                .filter(entry -> entry.getValue() <= maxDistance) // Фильтруем по дистанции
                .min(Comparator.comparingInt(entry -> entry.getKey().getHealth())) // Находим минимальное здоровье
                .map(Map.Entry::getKey) // Получаем Enemy
                .orElse(null); // Если ничего не найдено, возвращаем null
    }

    @Override
    public String toString() {
        return "EnemyDistanceList{" +
                "enemyDistanceList=" + enemyDistanceList +
                '}';
    }
}
