import constant.DriverMatchingType;
import constant.PaymentType;
import constant.PricingStrategy;
import constant.UserType;
import dto.ride.BookingResult;
import dto.ride.Ride;
import dto.ride.RideRequest;
import dto.user.Driver;
import dto.user.Location;
import dto.user.User;
import facade.RideBookingFacade;
import manager.RideManager;
import manager.SimpleRatingManager;
import repository.RideRepository;
import service.*;

import java.util.List;

/**
 * Demo: full flow using RideBookingFacade + RideManager + payment/notification/repository.
 */
public class Main {

    public static void main(String[] args) {
        ETAService etaService = new SimpleETAService();
        NotificationService notificationService = new SimpleNotificationService();
        RideBookingFacade facade = new RideBookingFacade(etaService, notificationService);

        RideManager rideManager = RideManager.getInstance();
        rideManager.setPaymentManager(new SimplePaymentManager());
        rideManager.setNotificationService(notificationService);
        RideRepository rideRepository = new RideRepository();
        rideManager.setRideRepository(rideRepository);

        User riderUser = new User(1, "Alice", UserType.RIDER);
        riderUser.setCurrentLocation(new Location("12.97", "77.59"));

        Driver driver1 = new Driver(2, "Bob");
        driver1.setLicenseNumber("L1");
        driver1.setVehicleNumber("V1");
        driver1.setCurrentLocation(new Location("12.96", "77.58"));
        driver1.updateRating(4.5f);

        Driver driver2 = new Driver(3, "Charlie");
        driver2.setLicenseNumber("L2");
        driver2.setVehicleNumber("V2");
        driver2.setCurrentLocation(new Location("12.98", "77.60"));
        driver2.updateRating(4.8f);

        Location from = new Location("12.97", "77.59");
        Location to = new Location("12.99", "77.61");

        // 1) Create booking (request + fare + matched drivers)
        BookingResult result = facade.createBooking(
                from, to, riderUser, PaymentType.UPI, PricingStrategy.DEFAULT, DriverMatchingType.RATING,
                List.of(driver1, driver2));

        RideRequest request = result.getRideRequest();
        System.out.println("Fare: " + request.getFare() + ", ETA mins: " + request.getExpectedTimeInMinutes());
        System.out.println("Matched drivers: " + result.getMatchedDrivers().size());

        // 2) Accept ride with first driver
        Ride ride = rideManager.acceptRide(request, result.getMatchedDrivers().get(0));
        System.out.println("Ride accepted, rideId: " + ride.getRideId());

        // 3) Start and end ride
        rideManager.startRide(ride);
        rideManager.endRide(ride);

        // 4) Rate driver
        SimpleRatingManager ratingManager = new SimpleRatingManager();
        ratingManager.rateDriver(ride, 5f);

        // 5) Ride history
        List<Ride> history = rideRepository.findByRider(riderUser);
        System.out.println("Rider ride history count: " + history.size());
    }
}
