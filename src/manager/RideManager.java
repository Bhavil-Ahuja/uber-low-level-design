package manager;

import constant.IdleStatus;
import constant.RideStatus;
import dto.ride.Ride;
import dto.ride.RideRequest;
import dto.user.User;
import repository.RideRepository;
import service.NotificationService;
import service.PaymentManager;

import java.time.LocalDateTime;

public class RideManager {

    private static volatile RideManager rideManagerInstance;

    private PaymentManager paymentManager;
    private NotificationService notificationService;
    private RideRepository rideRepository;

    private RideManager() {}

    public static RideManager getInstance() {
        if (rideManagerInstance == null) {
            synchronized (RideManager.class) {
                if (rideManagerInstance == null) {
                    rideManagerInstance = new RideManager();
                }
            }
        }
        return rideManagerInstance;
    }

    public void setPaymentManager(PaymentManager paymentManager) {
        this.paymentManager = paymentManager;
    }

    public void setNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void setRideRepository(RideRepository rideRepository) {
        this.rideRepository = rideRepository;
    }

    public synchronized Ride acceptRide(RideRequest rideRequest, User driver) {
        driver.updateIdleStatus(IdleStatus.BUSY);
        Ride ride = new Ride(rideRequest, driver);
        if (rideRepository != null) {
            rideRepository.save(ride);
        }
        if (notificationService != null) {
            notificationService.onDriverAssigned(ride);
        }
        return ride;
    }

    public void startRide(Ride ride) {
        ride.updateRideStatus(RideStatus.STARTED);
        if (notificationService != null) {
            notificationService.onRideStarted(ride);
        }
    }

    public void endRide(Ride ride) {
        ride.setEndTime(LocalDateTime.now());
        ride.updateRideStatus(RideStatus.ENDED);
        ride.getDriver().updateIdleStatus(IdleStatus.IDLE);
        if (paymentManager != null) {
            paymentManager.processPayment(ride);
        }
        if (notificationService != null) {
            notificationService.onRideEnded(ride);
        }
    }

    public void cancelRide(Ride ride) {
        ride.updateRideStatus(RideStatus.CANCELLED);
        if (ride.getDriver() != null) {
            ride.getDriver().updateIdleStatus(IdleStatus.IDLE);
        }
        ride.setDriver(null);
        ride.setStartTime(null);
        if (paymentManager != null) {
            paymentManager.processRefund(ride);
        }
        if (notificationService != null) {
            notificationService.onRideCancelled(ride);
        }
    }

    public void newDriverFound(Ride ride, User user) {
        ride.setDriver(user);
        ride.setStartTime(LocalDateTime.now());
    }
}
