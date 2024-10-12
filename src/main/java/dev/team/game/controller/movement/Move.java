package dev.team.game.controller.movement;

import dev.team.game.workvector.Coordinate;
import dev.team.models.Anomaly;
import dev.team.models.TransportResponse;
import dev.team.models.Vector2D;

import java.util.List;

public interface Move {
    public Vector2D getMaxAccelerationToPointWithoutAnomaly(TransportResponse transport, Coordinate point);
}
