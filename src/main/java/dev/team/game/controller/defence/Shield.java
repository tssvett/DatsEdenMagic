package dev.team.game.controller.defence;

import dev.team.models.Enemy;
import dev.team.models.TransportResponse;

import java.util.List;

public interface Shield {
    Boolean isNeedToActivateShield(TransportResponse myShip, List<Enemy> enemiesList);
}
