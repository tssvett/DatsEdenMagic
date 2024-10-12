package dev.team.game.controller.bountychoose;

import dev.team.game.workvector.Coordinate;
import dev.team.models.Bounty;
import dev.team.models.TransportResponse;

import java.util.ArrayList;
import java.util.List;

public class BountyChooseImp implements BountyChoose {

    @Override
    public Coordinate bountyChoose(TransportResponse transportResponse, List<Bounty> bountyList) {
        BountyList bountySortList = new BountyList(transportResponse,bountyList);

        return bountySortList.getMinDistanceBounty();
    }

    public Coordinate bountyChooseForCenter(TransportResponse transportResponse, List<Bounty> bountyList){
        BountyList bountySortList = new BountyList(transportResponse, bountyList);

        return  bountySortList.getCoordinateForCenterBounty();
    }
}
