package dto.ride;

import dto.user.User;

import java.util.List;

/**
 * Result of initiating a ride booking: the request (with fare) and matched drivers.
 */
public class BookingResult {

    private final RideRequest rideRequest;
    private final List<User> matchedDrivers;

    public BookingResult(RideRequest rideRequest, List<User> matchedDrivers) {
        this.rideRequest = rideRequest;
        this.matchedDrivers = matchedDrivers != null ? List.copyOf(matchedDrivers) : List.of();
    }

    public RideRequest getRideRequest() {
        return rideRequest;
    }

    public List<User> getMatchedDrivers() {
        return matchedDrivers;
    }
}
