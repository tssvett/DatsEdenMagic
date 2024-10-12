package dev.team.game.controller.bountychoose;

import dev.team.game.workvector.Coordinate;
import dev.team.models.Bounty;
import dev.team.models.TransportResponse;

import java.util.ArrayList;

public interface BountyChoose {
    public Coordinate bountyChoose(TransportResponse transportResponse, ArrayList<Bounty> bountyList);
}
