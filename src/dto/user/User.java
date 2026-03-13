package dto.user;

import constant.IdleStatus;
import constant.UserType;

public class User {

    private int id;
    private final String name;
    private final UserType userType;
    private Location currentLocation;
    private float rating;
    private IdleStatus idleStatus;

    public User() {
        this.id = 0;
        this.name = null;
        this.userType = null;
        this.currentLocation = null;
        this.rating = 0;
        this.idleStatus = IdleStatus.IDLE;
    }

    public User(int id, String name, UserType userType) {
        this.id = id;
        this.name = name;
        this.userType = userType;
        this.idleStatus = IdleStatus.IDLE;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public UserType getUserType() {
        return userType;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public float getRating() {
        return rating;
    }

    public void updateRating(float rating) {
        this.rating = rating;
    }

    public IdleStatus getIdleStatus() {
        return idleStatus;
    }

    public void updateIdleStatus(IdleStatus idleStatus) {
        this.idleStatus = idleStatus;
    }
}
