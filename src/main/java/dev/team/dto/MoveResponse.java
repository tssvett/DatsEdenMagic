package dev.team.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.team.models.ErrorDetails;
import dev.team.models.TransportResponse;
import dev.team.models.Vector2D;
import dev.team.models.Anomaly;
import dev.team.models.Bounty;
import dev.team.models.Enemy;
import dev.team.models.TransportRequest;

import java.util.List;

public record MoveResponse(
        List<Anomaly> anomalies,
        Integer attackCooldownMs,
        Integer attackDamage,
        Double attackExplosionRadius,
        Double attackRange,
        List<Bounty> bounties,
        List<Enemy> enemies,
        Vector2D mapSize,
        Double maxAccel,
        Double maxSpeed,
        String name,
        Integer points,
        Integer reviveTimeoutSec,
        Integer shieldCooldownMs,
        Integer shieldTimeMs,
        Integer transportRadius,
        List<TransportResponse> transports,
        List<Enemy> wantedList,
        @JsonIgnore
        List<ErrorDetails> errors
) {
}
