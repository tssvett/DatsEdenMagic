package dev.team.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Enemy {
    private Integer health;
    private Integer killBounty;
    private Integer shieldLeftMs;
    private Status status;
    private Vector2D velocity;
    private final Integer x;
    private final Integer y;
}
