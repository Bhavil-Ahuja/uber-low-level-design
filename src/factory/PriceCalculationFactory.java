package factory;

import constant.PricingStrategy;
import manager.*;

public class PriceCalculationFactory {

    public static PricingCalculator getPricingManager(PricingStrategy pricingStrategy) {
        if (pricingStrategy.equals(PricingStrategy.DEFAULT)) {
            return new SimpleFareManager();
        } else {
            return new SurgeFareManager(new SimpleFareManager());
        }
    }
}
