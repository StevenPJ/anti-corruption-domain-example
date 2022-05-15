package uk.co.mettle.common;

import lombok.Value;

import java.util.UUID;

public interface FincrimeClient {
    void check(CrimeCheckRequest crimeCheckRequest);

    @Value
    class CrimeCheckRequest {
        UUID paymentId;
        String aCrimeIndicator;
        int aNumber;
    }
}
