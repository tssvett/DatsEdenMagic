package dev.team.game.controller.bountychoose;

import dev.team.game.workvector.Coordinate;
import dev.team.models.Bounty;
import dev.team.models.TransportResponse;
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
        Bounty bounty=  bountyDistanceList.get(0).getKey();
        return new Coordinate(bounty.getX(), bounty.getY());
    }

}
