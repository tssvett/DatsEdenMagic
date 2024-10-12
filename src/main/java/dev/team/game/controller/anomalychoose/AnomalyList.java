package dev.team.game.controller.anomalychoose;

import dev.team.game.workvector.Coordinate;
import dev.team.models.Anomaly;
import dev.team.models.Enemy;
import dev.team.models.TransportResponse;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

public class AnomalyList {
    private TransportResponse transportResponse;

    @Getter
    private List<Map.Entry<Anomaly, Double>> anomalyDistanceList;

    public AnomalyList(TransportResponse transportResponse, List<Anomaly> anomalyList) {
        this.anomalyDistanceList = new ArrayList<>();
        this.transportResponse = transportResponse;
        for (Anomaly anomaly : anomalyList) {
            Coordinate coordinateTransport = new Coordinate(transportResponse.getX(), transportResponse.getY());
            Coordinate coordinateEnemy = new Coordinate(anomaly.getX(), anomaly.getY());

            addEnemyDistance(anomaly, Coordinate.distance(coordinateTransport, coordinateEnemy.coordinatePlusSpeed(anomaly.getVelocity(), 0.400)));
        }

        // Сортировка по health
        anomalyDistanceList.sort(Comparator.comparingDouble(Map.Entry::getValue));
    }

    // Метод для добавления новой пары
    public void addEnemyDistance(Anomaly anomaly, double distance) {
        anomalyDistanceList.add(new AbstractMap.SimpleEntry<>(anomaly, distance));
    }


    public List<Anomaly> getAnomaliesWithinSpeedDistance() {
        return anomalyDistanceList.stream()
                .filter(entry -> entry.getValue() < entry.getKey().getVelocity().length() * 2) // Фильтруем по условию
                .map(Map.Entry::getKey) // Получаем Anomaly
                .collect(Collectors.toList()); // Собираем в список
    }

    @Override
    public String toString() {
        return "EnemyDistanceList{" +
                "enemyDistanceList=" + anomalyDistanceList +
                '}';
    }
}


