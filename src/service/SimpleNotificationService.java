package service;

import dto.ride.Ride;
import dto.ride.RideRequest;
import dto.user.User;

public class SimpleNotificationService implements NotificationService {

    @Override
    public void onRideRequested(RideRequest request) {
        if (request != null && request.getRider() != null) {
            System.out.println("[Notification] Ride requested by " + request.getRider().getName() + " from " + request.getFrom() + " to " + request.getTo());
        }
    }

    @Override
    public void onDriverAssigned(Ride ride) {
        if (ride != null && ride.getDriver() != null && ride.getRider() != null) {
            System.out.println("[Notification] Driver " + ride.getDriver().getName() + " assigned to rider " + ride.getRider().getName());
        }
    }

    @Override
    public void onRideStarted(Ride ride) {
        if (ride != null) {
            System.out.println("[Notification] Ride started for " + ride.getRider().getName());
        }
    }

    @Override
    public void onRideEnded(Ride ride) {
        if (ride != null) {
            System.out.println("[Notification] Ride ended for " + ride.getRider().getName() + ", fare: " + ride.getFare());
        }
    }

    @Override
    public void onRideCancelled(Ride ride) {
        if (ride != null) {
            System.out.println("[Notification] Ride cancelled.");
        }
    }
}
