package dev.team.visualization.objectsdraw;

import dev.team.dto.MoveResponse;
import dev.team.models.Anomaly;
import dev.team.models.Bounty;
import dev.team.models.Enemy;
import dev.team.models.TransportResponse;
import dev.team.visualization.objectsdraw.drawable.DrawableObject;
import dev.team.visualization.objectsdraw.drawable.extend.DrawableAlly;
import dev.team.visualization.objectsdraw.drawable.extend.DrawableAnomaly;
import dev.team.visualization.objectsdraw.drawable.extend.DrawableBounty;
import dev.team.visualization.objectsdraw.drawable.extend.DrawableEnemy;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ConvertToListDrawObjects {
    public static List<DrawableObject> convertToListDrawObjects(MoveResponse moveResponse) {
        ArrayList<DrawableObject> drawObjects = new ArrayList<>();

        List<Enemy> enemies = moveResponse.enemies();
        List<TransportResponse> transports = moveResponse.transports();
        List<Anomaly> anomalies = moveResponse.anomalies();
        List<Bounty> bounties = moveResponse.bounties();

        for (Enemy enemy : enemies) {
            drawObjects.add(new DrawableEnemy(enemy.getX(), enemy.getY(), enemy.getHealth()));
        }

        for (TransportResponse transport : transports) {
            drawObjects.add(new DrawableAlly(transport.getX(), transport.getY(), transport.getHealth()));
        }

        for (Anomaly anomaly : anomalies) {
            drawObjects.add(new DrawableAnomaly(anomaly.getX(), anomaly.getY(), anomaly.getRadius().intValue(), anomaly.getEffectiveRadius().intValue(),anomaly.getStrength()));
        }

        for (Bounty bounty : bounties) {
            drawObjects.add(new DrawableBounty(bounty.getX(), bounty.getY(), bounty.getPoints()));
        }

        return drawObjects;
    }
}
