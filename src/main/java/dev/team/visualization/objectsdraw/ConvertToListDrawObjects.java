package dev.team.visualization.objectsdraw;

import dev.team.dto.MoveResponse;
import dev.team.models.Anomaly;
import dev.team.models.Bounty;
import dev.team.models.Enemy;
import dev.team.models.TransportResponse;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ConvertToListDrawObjects {
    public static ArrayList<DrawableObject> convertToListDrawObjects(MoveResponse moveResponse) {
        ArrayList<DrawableObject> drawObjects = new ArrayList<>();

        List<Enemy> enemies = moveResponse.enemies();
        List<TransportResponse> transports = moveResponse.transports();
        List<Anomaly> anomalies = moveResponse.anomalies();
        List<Bounty> bounties = moveResponse.bounties();

        for (Enemy enemy : enemies) {
            drawObjects.add(new DrawableObject(enemy.getX(), enemy.getY(), 2, Color.RED, 0, null));
        }

        for (TransportResponse transport : transports) {
            drawObjects.add(new DrawableObject(transport.getX(), transport.getY(), 2, Color.BLUE, 0, null));
        }

        for (Anomaly anomaly : anomalies) {
            Color outerColor = (anomaly.getStrength() > 0) ? Color.GREEN : Color.CYAN;
            drawObjects.add(new DrawableObject(anomaly.getX(), anomaly.getY(), anomaly.getRadius().intValue(), Color.BLACK, anomaly.getEffectiveRadius().intValue(), outerColor));
        }

        for (Bounty bounty : bounties) {
            drawObjects.add(new DrawableObject(bounty.getX(), bounty.getY(), bounty.getRadius(), Color.YELLOW, 0, null));
        }

        return drawObjects;
    }
}
