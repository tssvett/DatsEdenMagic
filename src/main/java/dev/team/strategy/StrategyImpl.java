package dev.team.strategy;

import dev.team.dto.MoveRequest;
import dev.team.dto.MoveResponse;
import dev.team.game.controller.attack.Attack;
import dev.team.game.controller.attack.AttackImpl;
import dev.team.game.controller.bountychoose.BountyChoose;
import dev.team.game.controller.bountychoose.BountyChooseImp;
import dev.team.game.controller.defence.Shield;
import dev.team.game.controller.defence.ShieldImpl;
import dev.team.game.controller.movement.Move;
import dev.team.game.controller.movement.MoveImpl;
import dev.team.game.workvector.Coordinate;
import dev.team.models.Bounty;
import dev.team.models.Enemy;
import dev.team.models.TransportRequest;
import dev.team.models.TransportResponse;
import dev.team.models.Vector2D;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class StrategyImpl implements Strategy {
    private final static int CENTER_LOW = 6000;
    private final static int CENTER_HIGH = 9000;

    @Override
    public MoveRequest makeStrategyStep(MoveResponse moveResponse) {
        Attack attack = new AttackImpl();
        Shield shield = new ShieldImpl();
        Move move = new MoveImpl();
        BountyChoose bountyChoose = new BountyChooseImp();

        List<TransportResponse> myShips = moveResponse.transports();
        List<Enemy> enemies = moveResponse.enemies();
        List<Bounty> bounties = moveResponse.bounties();
        boolean isReachPoint = true;

        List<TransportRequest> moveRequests = new ArrayList<>();
        int i = 1;
        for (TransportResponse myShip : myShips) {
            Coordinate coordinates = attack.getCoordinatesForAttack(myShip, enemies);
            boolean needToActivateShield = shield.isNeedToActivateShieldWhenHpIsLow(myShip, enemies);

            Coordinate purposeCoordinate = new Coordinate(0, 0);


            Vector2D acceleration;

            LocalTime currentTime = LocalTime.now();

            // Получаем минуты текущего времени
            int currentMinutes = currentTime.getMinute();


            if (currentMinutes < 7) {
                Coordinate nearestMoneyCoordinates = bountyChoose.bountyChoose(myShip, bounties);
                if (myShip.getVelocity().length() < 20) {
                    acceleration = move.getAccelerationToPointForSmallSpeed(myShip, nearestMoneyCoordinates);
                } else {
                    acceleration = move.getAccelerationToPointForBigSpeed(myShip, nearestMoneyCoordinates);
                }
                moveRequests.add(new TransportRequest(
                        acceleration,
                        needToActivateShield,
                        coordinates,
                        myShip.getId()
                ));
            } else {
                if (
                        (myShip.getX() <= CENTER_LOW || myShip.getX() >= CENTER_HIGH)
                                || ((myShip.getY() <= CENTER_LOW || myShip.getY() >= CENTER_HIGH))
                ) {
                    Coordinate nearestMoneyCoordinates = bountyChoose.bountyChooseForCenter(myShip, bounties);
                    log.info("Корабль {} иду в координату ({} {})\n", i, nearestMoneyCoordinates.getX(), nearestMoneyCoordinates.getY());
                    if (myShip.getVelocity().length() < 20) {
                        acceleration = move.getAccelerationToPointForSmallSpeed(myShip, nearestMoneyCoordinates);
                    } else {
                        acceleration = move.getAccelerationToPointForBigSpeed(myShip, nearestMoneyCoordinates);
                    }
                    moveRequests.add(new TransportRequest(
                            acceleration,
                            needToActivateShield,
                            coordinates,
                            myShip.getId()
                    ));
                } else {
                    Coordinate nearestMoneyCoordinates = bountyChoose.bountyChoose(myShip, bounties);
                    if (myShip.getVelocity().length() < 20) {
                        acceleration = move.getAccelerationToPointForSmallSpeed(myShip, nearestMoneyCoordinates);
                    } else {
                        acceleration = move.getAccelerationToPointForBigSpeed(myShip, nearestMoneyCoordinates);
                    }
                    moveRequests.add(new TransportRequest(
                            acceleration,
                            needToActivateShield,
                            coordinates,
                            myShip.getId()
                    ));
                }


            }
            i++;
        }
        return new MoveRequest(moveRequests);
    }
}
