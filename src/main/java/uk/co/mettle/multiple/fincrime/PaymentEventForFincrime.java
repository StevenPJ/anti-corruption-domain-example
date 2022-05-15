package uk.co.mettle.multiple.fincrime;

import lombok.Value;

import java.util.UUID;

@Value
public class PaymentEventForFincrime {
    UUID paymentId;
    String crimeIndicator;
    boolean completed;
}
