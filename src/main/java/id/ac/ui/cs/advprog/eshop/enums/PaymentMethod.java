package id.ac.ui.cs.advprog.eshop.enums;

import lombok.Getter;

@Getter
public enum PaymentMethod {

    VOUCHER("VOUCHER"),
    COD("CASH_ON_DELIVERY");

    private final String value;
    private PaymentMethod(String value) {
        this.value = value;
    }

    public static boolean contains(String param) {
        for (PaymentMethod paymentMethods : PaymentMethod.values()) {
            if (paymentMethods.name().equals(param)) {
                return true;
            }
        }
        return false;
    }
}
