package dev.team.dto;

import dev.team.models.Vector2D;
import dev.team.models.Anomaly;
import dev.team.models.Bounty;
import dev.team.models.Enemy;
import dev.team.models.Transport;

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
        Double points,
        Integer reviveTimeoutSec,
        Integer shieldCooldownSec,
        Integer shieldTimeMs,
        Integer transportRadius,
        List<Transport> transports,
        List<Enemy> wantedList
) {
}
