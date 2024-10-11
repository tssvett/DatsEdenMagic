package dev.team.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Transport {
    private Vector2D acceleration;
    private boolean activateShield;
    private final Vector2D attack;
    private final String id;
}
