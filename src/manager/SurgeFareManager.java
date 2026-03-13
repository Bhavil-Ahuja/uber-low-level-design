package manager;

import dto.ride.RideRequest;

public class SurgeFareManager implements PricingCalculator {

    PricingCalculator pricingCalculator;
    public SurgeFareManager(PricingCalculator pricingCalculator) {
        this.pricingCalculator = pricingCalculator;
    }

    @Override
    public double calculateFare(RideRequest rideRequest) {
        return pricingCalculator.calculateFare(rideRequest) + 30;
    }
}
