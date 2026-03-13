package manager;

import constant.RideStatus;
import dto.ride.Ride;

public class SimpleRatingManager implements RatingManager {

    private static final float MIN_RATING = 0f;
    private static final float MAX_RATING = 5f;

    @Override
    public void rateDriver(Ride ride, float rating) {
        if (ride == null || ride.getDriver() == null) {
            return;
        }
        if (ride.getRideStatus() != RideStatus.ENDED) {
            return;
        }
        float clamped = clamp(rating);
        ride.getDriver().updateRating(clamped);
    }

    @Override
    public void rateRider(Ride ride, float rating) {
        if (ride == null || ride.getRider() == null) {
            return;
        }
        if (ride.getRideStatus() != RideStatus.ENDED) {
            return;
        }
        float clamped = clamp(rating);
        ride.getRider().updateRating(clamped);
    }

    private static float clamp(float value) {
        if (value < MIN_RATING) return MIN_RATING;
        if (value > MAX_RATING) return MAX_RATING;
        return value;
    }
}
