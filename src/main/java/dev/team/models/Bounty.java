package dev.team.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
public class Bounty {
    private Integer points;
    private Integer radius;
    private Integer x;
    private Integer y;
}
