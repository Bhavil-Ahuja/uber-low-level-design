package manager;

import dto.ride.Ride;
import dto.user.User;

/**
 * Handles post-ride ratings (rider rates driver; optionally driver rates rider).
 */
public interface RatingManager {

    /**
     * Rider rates the driver after the ride.
     * @param ride completed ride
     * @param rating 1-5 (or 0-5 depending on product)
     */
    void rateDriver(Ride ride, float rating);

    /**
     * Driver rates the rider (optional).
     */
    void rateRider(Ride ride, float rating);
}
