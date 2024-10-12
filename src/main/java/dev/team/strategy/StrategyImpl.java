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
import dev.team.models.Anomaly;
import dev.team.models.Bounty;
import dev.team.models.Enemy;
import dev.team.models.TransportRequest;
import dev.team.models.TransportResponse;
import dev.team.models.Vector2D;

import java.util.ArrayList;
import java.util.List;

public class StrategyImpl implements Strategy {
    @Override
    public MoveRequest makeStrategyStep(MoveResponse moveResponse) {
        Attack attack = new AttackImpl();
        Shield shield = new ShieldImpl();
        Move move = new MoveImpl();
        BountyChoose bountyChoose = new BountyChooseImp();

        List<TransportResponse> myShips = moveResponse.transports();
        List<Enemy> enemies = moveResponse.enemies();
        List<Bounty> bounties = moveResponse.bounties();

        List<TransportRequest> moveRequests = new ArrayList<>();
        for (TransportResponse myShip : myShips) {
            Coordinate coordinates = attack.getCoordinatesForAttack(myShip, enemies);
            boolean needToActivateShield = shield.isNeedToActivateShield(myShip, enemies);


            Coordinate nearestMoneyCoordinates = bountyChoose.bountyChoose(myShip, bounties);
            Vector2D acceleration = move.getAccelerationToPoint(myShip, nearestMoneyCoordinates);
            System.out.println(acceleration.toString());
            moveRequests.add(new TransportRequest(
                    acceleration,
                    needToActivateShield,
                    coordinates,
                    myShip.getId()
            ));
        }
        return new MoveRequest(moveRequests);
    }
}
