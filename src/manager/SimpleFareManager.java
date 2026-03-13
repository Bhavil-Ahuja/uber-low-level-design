package manager;

import dto.ride.RideRequest;

import java.time.temporal.ChronoUnit;

public class SimpleFareManager implements PricingCalculator {

    @Override
    public double calculateFare(RideRequest rideRequest) {
        return 10 * rideRequest.getExpectedTimeInMinutes();
    }
}
