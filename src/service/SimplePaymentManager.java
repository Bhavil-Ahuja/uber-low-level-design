package service;

import constant.RideStatus;
import dto.ride.Ride;

public class SimplePaymentManager implements PaymentManager {

    @Override
    public boolean processPayment(Ride ride) {
        if (ride == null || ride.getRideStatus() != RideStatus.ENDED) {
            return false;
        }
        // In a real system: call payment gateway with ride.getPreferredPaymentType(), ride.getFare(), ride.getRider()
        return true;
    }

    @Override
    public boolean processRefund(Ride ride) {
        if (ride == null) {
            return true;
        }
        // In a real system: refund if any amount was charged
        return true;
    }
}
