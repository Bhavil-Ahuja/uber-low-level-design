package manager;

import dto.ride.RideRequest;

public interface PricingCalculator {
    double calculateFare(RideRequest rideRequest);
}
