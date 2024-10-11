package dev.team.dto;

import dev.team.models.Transport;

import java.util.List;

public record MoveRequest(
        List<Transport> transports
) {
}
