package service;

import dto.user.Location;

/**
 * Provides estimated time of arrival / expected trip duration.
 */
public interface ETAService {

    /**
     * Expected trip duration in minutes from pickup to destination.
     */
    int getExpectedTimeInMinutes(Location from, Location to);
}
