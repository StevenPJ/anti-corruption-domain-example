package uk.co.mettle.multiple.fincrime;

import lombok.Value;

import java.util.UUID;

public interface Fincrime {
    void check(PaymentCrimeRequest crimeCheckRequest);

    @Value
    class PaymentCrimeRequest {
        UUID paymentId;
        String fraudIndiciator;
    }
}
