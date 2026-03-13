package manager;

import dto.ride.RideRequest;
import dto.user.User;

import java.util.List;

public interface DriverMatchingManager {
    List<User> getDrivers(RideRequest rideRequest, List<User> drivers);
}
