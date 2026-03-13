package service;

import dto.user.Location;
import util.DistanceUtil;

public class SimpleETAService implements ETAService {

    @Override
    public int getExpectedTimeInMinutes(Location from, Location to) {
        return DistanceUtil.estimatedMinutes(from, to);
    }
}
