package dto.ride;

import constant.PaymentType;
import constant.RideStatus;
import dto.user.Location;
import dto.user.User;

import java.time.LocalDateTime;

public class RideRequest {

    private final Location from;
    private final Location to;
    private final User rider;
    private final LocalDateTime requestedTime;
    private final int expectedTimeInMinutes;
    private final PaymentType preferredPaymentType;
    private RideStatus rideStatus;
    private double fare;

    /** Constructor for copy (e.g. Ride from RideRequest). */
    public RideRequest(RideRequest rideRequest) {
        this.from = rideRequest.from;
        this.to = rideRequest.to;
        this.rider = rideRequest.rider;
        this.requestedTime = rideRequest.requestedTime;
        this.preferredPaymentType = rideRequest.preferredPaymentType;
        this.rideStatus = rideRequest.rideStatus;
        this.expectedTimeInMinutes = rideRequest.expectedTimeInMinutes;
        this.fare = rideRequest.fare;
    }

    public RideRequest(Location from, Location to, User rider, LocalDateTime requestedTime, PaymentType preferredPaymentType, int expectedTimeInMinutes, double fare) {
        this.from = from;
        this.to = to;
        this.rider = rider;
        this.requestedTime = requestedTime;
        this.preferredPaymentType = preferredPaymentType;
        this.rideStatus = RideStatus.REQUESTED;
        this.expectedTimeInMinutes = expectedTimeInMinutes;
        this.fare = fare;
    }

    /** Constructor without fare (fare to be set via setFare after ETA/pricing). */
    public RideRequest(Location from, Location to, User rider, LocalDateTime requestedTime, PaymentType preferredPaymentType, int expectedTimeInMinutes) {
        this.from = from;
        this.to = to;
        this.rider = rider;
        this.requestedTime = requestedTime;
        this.preferredPaymentType = preferredPaymentType;
        this.rideStatus = RideStatus.REQUESTED;
        this.expectedTimeInMinutes = expectedTimeInMinutes;
        this.fare = 0.0;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public RideStatus getRideStatus() {
        return rideStatus;
    }

    public Location getFrom() {
        return from;
    }

    public Location getTo() {
        return to;
    }

    public User getRider() {
        return rider;
    }

    public PaymentType getPreferredPaymentType() {
        return preferredPaymentType;
    }

    public LocalDateTime getRequestedTime() {
        return requestedTime;
    }

    public LocalDateTime setRequestedTime(LocalDateTime requestedTime) {
        return requestedTime;
    }

    public double getFare() {
        return fare;
    }

    public int getExpectedTimeInMinutes() {
        return expectedTimeInMinutes;
    }

    public void updateRideStatus(RideStatus rideStatus) {
        this.rideStatus = rideStatus;
    }
}
