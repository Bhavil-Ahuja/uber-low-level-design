package manager;

import constant.IdleStatus;
import dto.ride.RideRequest;
import dto.user.User;
import util.DistanceUtil;

import java.util.Comparator;
import java.util.List;

public class DistanceBasedDriverMatching implements DriverMatchingManager {

    private static final int MAX_DRIVERS_RETURNED = 5;

    @Override
    public List<User> getDrivers(RideRequest rideRequest, List<User> drivers) {
        if (rideRequest == null || rideRequest.getFrom() == null || drivers == null) {
            return List.of();
        }
        return drivers.stream()
                .filter(d -> IdleStatus.IDLE.equals(d.getIdleStatus()))
                .filter(d -> d.getCurrentLocation() != null)
                .sorted(Comparator.comparingDouble(d -> DistanceUtil.distanceKm(rideRequest.getFrom(), d.getCurrentLocation())))
                .limit(MAX_DRIVERS_RETURNED)
                .toList();
    }
}
