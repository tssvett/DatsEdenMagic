package dev.team.game.controller.movement;

import dev.team.models.Anomaly;
import dev.team.models.TransportResponse;
import dev.team.models.Vector2D;

import java.util.List;

public interface Move {
    Vector2D getAccelerationToPoint(TransportResponse myShip, Vector2D targetPosition, Vector2D anomalyAcceleration);
}
