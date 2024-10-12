package dev.team.game.controller.attack;

import dev.team.game.controller.attack.enemychoose.EnemyChooseImp;
import dev.team.game.workvector.Coordinate;
import dev.team.models.Enemy;
import dev.team.models.TransportResponse;
import dev.team.models.Vector2D;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class AttackImpl implements Attack {

    private static final double ATTACK_RANGE = 200.0; // Радиус дальности
    private static final double ATTACK_RADIUS = 20.0;  // Радиус поражения
    private static final int DAMAGE = 30;              // Урон
    private static final int COOLDOWN_MS = 5000;       // Время восстановления в миллисекундах

    /**
     * @param myShip      Выбранный кораблик
     * @param enemiesList Список врагов, которых мы видим
     * @return Координаты для атаки
     */
    @Override
    public Coordinate getCoordinatesForAttack(TransportResponse myShip, List<Enemy> enemiesList) {
        if (enemiesList == null || enemiesList.isEmpty()) {
            log.info("Корабль {} не имеет врагов для атаки", myShip.getId());
            return null; // Нет врагов для атакиЫ
        }

        // Проверяем, готов ли ковер к атаке
        if (myShip.getAttackCooldownMs() > 0) {
            log.info("Ковер {} перезаряжается", myShip.getId());
            return null; // Ковер не готов к атаке
        }
        EnemyChooseImp chooseEnemy = new EnemyChooseImp();
        Enemy enemy = chooseEnemy.chooseEnemy(myShip,enemiesList);
        if (enemy!=null) {
            Coordinate coordinateAttack = new Coordinate(enemy.getX(), enemy.getY());
            return coordinateAttack.coordinatePlusSpeed(enemy.getVelocity(), 0.4);// Возвращаем координаты врага для атаки. Ебашим по врагу
        }


        log.info("Ковер {} не имеет врагов для атаки",myShip.getId());
        return null;
}

//    private Vector2D getVectorForAttack(Enemy enemy)

// Метод для вычисления расстояния между двумя точками
private double calculateDistance(int x1, int y1, int x2, int y2) {
    return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
}
}