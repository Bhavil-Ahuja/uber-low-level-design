package repository;

import dto.ride.Ride;
import dto.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * In-memory ride history. Can be replaced by a DB-backed implementation.
 */
public class RideRepository {

    private final Map<Integer, Ride> ridesById = new ConcurrentHashMap<>();
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    public Ride save(Ride ride) {
        if (ride == null) {
            return null;
        }
        int id = idGenerator.getAndIncrement();
        ride.setRideId(id);
        ridesById.put(id, ride);
        return ride;
    }

    public Optional<Ride> findById(int id) {
        return Optional.ofNullable(ridesById.get(id));
    }

    public List<Ride> findByRider(User rider) {
        if (rider == null) return List.of();
        int riderId = rider.getId();
        return ridesById.values().stream()
                .filter(r -> r.getRider() != null && r.getRider().getId() == riderId)
                .toList();
    }

    public List<Ride> findByDriver(User driver) {
        if (driver == null) return List.of();
        int driverId = driver.getId();
        return ridesById.values().stream()
                .filter(r -> r.getDriver() != null && r.getDriver().getId() == driverId)
                .toList();
    }

    public List<Ride> findAll() {
        return new ArrayList<>(ridesById.values());
    }
}
