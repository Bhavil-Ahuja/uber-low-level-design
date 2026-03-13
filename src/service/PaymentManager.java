package service;

import dto.ride.Ride;

/**
 * Handles payment processing for rides (charge on completion, refund on cancel).
 */
public interface PaymentManager {

    /**
     * Process payment for a completed ride.
     * @return true if payment succeeded
     */
    boolean processPayment(Ride ride);

    /**
     * Process refund when a ride is cancelled (if payment was pre-auth or partial).
     * @return true if refund succeeded or no refund needed
     */
    boolean processRefund(Ride ride);
}
