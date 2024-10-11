package dev.team.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class TransportRequest {
    private Vector2D acceleration;
    private boolean activateShield;
    private Vector2D attack;
    private String id;
}
