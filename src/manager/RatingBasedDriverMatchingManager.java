package manager;

import constant.IdleStatus;
import dto.ride.RideRequest;
import dto.user.User;

import java.util.ArrayList;
import java.util.List;

public class RatingBasedDriverMatchingManager implements DriverMatchingManager {

    @Override
    public List<User> getDrivers(RideRequest rideRequest, List<User> drivers) {
        List<User> sortedDrivers = new ArrayList<>(drivers);
        sortedDrivers.sort((driver1, driver2) -> Float.compare(driver2.getRating(), driver1.getRating()));
        return sortedDrivers.stream().filter(driver -> IdleStatus.IDLE.equals(driver.getIdleStatus())).limit(5).toList();
    }
}
