package service;

import dto.ride.Ride;
import dto.ride.RideRequest;
import dto.user.User;

/**
 * Notifications for ride lifecycle events.
 */
public interface NotificationService {

    void onRideRequested(RideRequest request);

    void onDriverAssigned(Ride ride);

    void onRideStarted(Ride ride);

    void onRideEnded(Ride ride);

    void onRideCancelled(Ride ride);
}
