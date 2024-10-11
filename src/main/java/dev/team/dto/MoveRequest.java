package dev.team.dto;

import dev.team.models.TransportRequest;

import java.util.List;

public record MoveRequest(
        List<TransportRequest> transports
) {
}
