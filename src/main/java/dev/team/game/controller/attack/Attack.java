package dev.team.game.controller.attack;

import dev.team.game.workvector.Coordinate;
import dev.team.models.Enemy;
import dev.team.models.TransportResponse;
import dev.team.models.Vector2D;

import java.util.List;

public interface Attack {

    Coordinate getCoordinatesForAttack(TransportResponse myShip, List<Enemy> enemiesList);
}
