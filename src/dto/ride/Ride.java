package dto.ride;

import constant.RideStatus;
import dto.user.User;

import java.time.LocalDateTime;

public class Ride extends RideRequest {

    private Integer rideId;
    private User driver;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Integer getRideId() {
        return rideId;
    }

    public void setRideId(Integer rideId) {
        this.rideId = rideId;
    }

    public Ride(RideRequest rideRequest, User assignedDriver) {
        super(rideRequest);
        this.updateRideStatus(RideStatus.ACCEPTED);
        this.driver = assignedDriver;
        this.startTime = LocalDateTime.now();
        this.endTime = null;
    }

    public User getDriver() {
        return driver;
    }

    public void setDriver(User driver) {
        this.driver = driver;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
}
