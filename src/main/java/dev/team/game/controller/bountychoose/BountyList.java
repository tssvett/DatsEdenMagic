package dev.team.game.controller.bountychoose;

import dev.team.game.workvector.Coordinate;
import dev.team.models.Bounty;
import dev.team.models.TransportResponse;
import dev.team.models.Vector2D;
import lombok.Getter;

import java.util.*;

public class BountyList {
    private TransportResponse transportResponse;

    @Getter
    private List<Map.Entry<Bounty, Double>> bountyDistanceList;

    public BountyList(TransportResponse transportResponse, List<Bounty> bountyList) {
        this.bountyDistanceList = new ArrayList<>();
        this.transportResponse = transportResponse;
        for (Bounty bounty : bountyList) {
            Coordinate coordinateTransport = new Coordinate(transportResponse.getX(), transportResponse.getY());
            Coordinate coordinateBounty = new Coordinate(bounty.getX(), bounty.getY());
            addBountyDistance(bounty, Coordinate.distance(coordinateTransport, coordinateBounty));
        }

        // Сортировка по distance
        Collections.sort(bountyDistanceList, Comparator.comparingDouble(Map.Entry::getValue));
    }

    // Метод для добавления новой пары
    public void addBountyDistance(Bounty bounty, double distance) {
        bountyDistanceList.add(Map.entry(bounty, distance));
    }

    public Coordinate getMinDistanceBounty() {
        Vector2D vector2D = transportResponse.getVelocity();

        return bountyDistanceList.stream()
                .filter(entry -> {
                    Bounty bounty = entry.getKey();
                    Vector2D bountyVector = new Vector2D((double) (bounty.getX() - transportResponse.getX()), (double) (bounty.getY() - transportResponse.getY()));
                    double angle = vector2D.angleVectors(bountyVector); // Предполагается, что метод angleVectors реализован
                    return angle <= 90; // Фильтруем по углу (<= 90 градусов)
                })
                .min(Comparator.comparingDouble(Map.Entry::getValue)) // Находим минимальную дистанцию
                .map(Map.Entry::getKey) // Получаем Bounty
                .map(bounty -> new Coordinate(bounty.getX(), bounty.getY())) // Преобразуем в Coordinate
                .orElseGet(() -> {
                    if (!bountyDistanceList.isEmpty()) {
                        return new Coordinate(bountyDistanceList.get(0).getKey().getX(),
                                bountyDistanceList.get(0).getKey().getY());
                    }
                    else
                        return null;
                }); // Если ничего не найдено, возвращаем null
                }

    }
