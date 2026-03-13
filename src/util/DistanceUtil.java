package util;

import dto.user.Location;

/**
 * Distance and duration utilities (e.g. Haversine for lat/long).
 */
public final class DistanceUtil {

    private static final double EARTH_RADIUS_KM = 6371.0;
    private static final double DEFAULT_AVERAGE_SPEED_KMH = 30.0;

    private DistanceUtil() {}

    /**
     * Distance between two locations in kilometres (Haversine formula).
     */
    public static double distanceKm(Location a, Location b) {
        double lat1 = parseDouble(a.getLatitude(), 0.0);
        double lon1 = parseDouble(a.getLongitude(), 0.0);
        double lat2 = parseDouble(b.getLatitude(), 0.0);
        double lon2 = parseDouble(b.getLongitude(), 0.0);
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double x = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(x), Math.sqrt(1 - x));
        return EARTH_RADIUS_KM * c;
    }

    /**
     * Estimated time in minutes between two locations at default average speed.
     */
    public static int estimatedMinutes(Location from, Location to) {
        return estimatedMinutes(from, to, DEFAULT_AVERAGE_SPEED_KMH);
    }

    /**
     * Estimated time in minutes between two locations at given average speed (km/h).
     */
    public static int estimatedMinutes(Location from, Location to, double averageSpeedKmh) {
        double distKm = distanceKm(from, to);
        if (averageSpeedKmh <= 0) {
            averageSpeedKmh = DEFAULT_AVERAGE_SPEED_KMH;
        }
        double hours = distKm / averageSpeedKmh;
        return Math.max(1, (int) Math.round(hours * 60));
    }

    /** Alias for clarity when used with two locations. */
    public static double distanceKmBetween(Location from, Location to) {
        return distanceKm(from, to);
    }

    private static double parseDouble(String s, double defaultValue) {
        if (s == null || s.isBlank()) {
            return defaultValue;
        }
        try {
            return Double.parseDouble(s.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
