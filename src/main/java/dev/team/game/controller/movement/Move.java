package dev.team.game.controller.movement;

import dev.team.game.workvector.Coordinate;
import dev.team.models.TransportResponse;
import dev.team.models.Vector2D;

public interface Move {
    public Vector2D getMaxAccelerationToPointWithoutAnomaly(TransportResponse transport, Coordinate point);

    public Vector2D getAccelerationToPointForSmallSpeed(
            TransportResponse transportResponse, Coordinate point);
    public Vector2D getAccelerationToPointForBigSpeed(
            TransportResponse transportResponse, Coordinate point);
}
