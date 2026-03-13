package dto.user;

import constant.PaymentType;

public class Rider extends User {

    private PaymentType preferredPaymentType;

    public PaymentType getPreferredPaymentType() {
        return preferredPaymentType;
    }

    public void setPreferredPaymentType(PaymentType preferredPaymentType) {
        this.preferredPaymentType = preferredPaymentType;
    }
}
