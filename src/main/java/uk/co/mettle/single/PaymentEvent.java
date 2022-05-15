package uk.co.mettle.single;

import lombok.Value;

import java.util.UUID;

@Value
class PaymentEvent {
    UUID paymentId;
    String fraudInidcator;
    boolean completed;
    int aNumber;
}
