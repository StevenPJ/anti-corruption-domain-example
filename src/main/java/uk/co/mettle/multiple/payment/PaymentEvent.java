package uk.co.mettle.multiple.payment;

import lombok.Value;

import java.util.UUID;

@Value
public class PaymentEvent {
    UUID paymentId;
    boolean completed;
}
