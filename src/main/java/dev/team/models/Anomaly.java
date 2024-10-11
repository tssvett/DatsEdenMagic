package dev.team.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Anomaly {
    private Double effectiveRadius;
    private String id;
    private Double radius;
    private Double strength;
    private Vector2D velocity;
    private final Integer x;
    private final Integer y;
}
