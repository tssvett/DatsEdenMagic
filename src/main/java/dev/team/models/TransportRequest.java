package dev.team.models;

import dev.team.game.workvector.Coordinate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class TransportRequest {
    private Vector2D acceleration;
    private boolean activateShield;
    private Coordinate attack;
    private String id;
}
