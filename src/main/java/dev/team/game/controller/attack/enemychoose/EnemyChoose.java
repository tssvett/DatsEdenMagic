package dev.team.game.controller.attack.enemychoose;

import dev.team.game.workvector.Coordinate;
import dev.team.models.Enemy;
import dev.team.models.TransportResponse;

import java.util.List;

public interface EnemyChoose {
    public Enemy chooseEnemy(TransportResponse transportResponse, List<Enemy> enemies);
}
