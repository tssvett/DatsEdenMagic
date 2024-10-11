package dev.team.models;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
public class Bounty {
    private Integer points;
    private Integer radius;
    private final Integer x;
    private final Integer y;
}
