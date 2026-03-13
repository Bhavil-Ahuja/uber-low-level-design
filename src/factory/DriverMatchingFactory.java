package factory;

import constant.DriverMatchingType;
import manager.DistanceBasedDriverMatching;
import manager.DriverMatchingManager;
import manager.RatingBasedDriverMatchingManager;

public class DriverMatchingFactory {

    public static DriverMatchingManager getDriverMatchingManager(DriverMatchingType driverMatchingType) {
        if (driverMatchingType.equals(DriverMatchingType.DISTANCE)) {
            return new DistanceBasedDriverMatching();
        } else {
            return new RatingBasedDriverMatchingManager();
        }
    }
}
