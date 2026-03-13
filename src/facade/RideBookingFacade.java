package facade;

import constant.DriverMatchingType;
import constant.PaymentType;
import constant.PricingStrategy;
import dto.ride.BookingResult;
import dto.ride.RideRequest;
import dto.user.Location;
import dto.user.User;
import factory.DriverMatchingFactory;
import factory.PriceCalculationFactory;
import manager.DriverMatchingManager;
import manager.PricingCalculator;
import service.ETAService;
import service.NotificationService;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Facade that orchestrates: create ride request, ETA, fare calculation, and driver matching.
 * Client uses this to start a booking, then uses RideManager to accept/start/end/cancel.
 */
public class RideBookingFacade {

    private final ETAService etaService;
    private final NotificationService notificationService;

    public RideBookingFacade(ETAService etaService, NotificationService notificationService) {
        this.etaService = etaService;
        this.notificationService = notificationService;
    }

    /**
     * Creates a ride request with ETA and fare, finds matching drivers, and notifies.
     *
     * @param from                pickup location
     * @param to                  destination
     * @param rider               rider
     * @param preferredPaymentType payment preference
     * @param pricingStrategy     DEFAULT or SURGE
     * @param driverMatchingType  DISTANCE or RATING
     * @param allDrivers          pool of drivers to match from
     * @return request (with fare set) and list of matched drivers (best first)
     */
    public BookingResult createBooking(
            Location from,
            Location to,
            User rider,
            PaymentType preferredPaymentType,
            PricingStrategy pricingStrategy,
            DriverMatchingType driverMatchingType,
            List<User> allDrivers) {

        int expectedTimeInMinutes = etaService.getExpectedTimeInMinutes(from, to);
        LocalDateTime requestedTime = LocalDateTime.now();

        RideRequest request = new RideRequest(from, to, rider, requestedTime, preferredPaymentType, expectedTimeInMinutes);
        PricingCalculator pricingCalculator = PriceCalculationFactory.getPricingManager(pricingStrategy);
        request.setFare(pricingCalculator.calculateFare(request));

        DriverMatchingManager matchingManager = DriverMatchingFactory.getDriverMatchingManager(driverMatchingType);
        List<User> matchedDrivers = matchingManager.getDrivers(request, allDrivers != null ? allDrivers : List.of());

        notificationService.onRideRequested(request);

        return new BookingResult(request, matchedDrivers);
    }
}
