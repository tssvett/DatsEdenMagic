package dev.team.strategy;

import dev.team.dto.MoveRequest;
import dev.team.dto.MoveResponse;

public interface Strategy {

    public MoveRequest makeStrategyStep(MoveResponse moveResponse);
}
