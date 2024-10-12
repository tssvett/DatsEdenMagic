package dev.team.game.controller.bountychoose;

import dev.team.models.Bounty;
import dev.team.models.TransportResponse;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BountyList {
    private TransportResponse transportResponse;
    // Метод для получения всех пар
    @Getter
    private Map<Bounty, Double> bountyDistanceMap;

    public BountyList() {
        this.bountyDistanceMap = new HashMap<>();
    }

    // Метод для добавления новой пары
    public void addBountyDistance(Bounty bounty, double distance) {
        bountyDistanceMap.put(bounty, distance);
    }

    // Метод для получения расстояния по объекту Bounty
    public Double getDistance(Bounty bounty) {
        return bountyDistanceMap.get(bounty);
    }

    @Override
    public String toString() {
        return "BountyDistanceMap{" +
                "bountyDistanceMap=" + bountyDistanceMap +
                '}';
    }


}
