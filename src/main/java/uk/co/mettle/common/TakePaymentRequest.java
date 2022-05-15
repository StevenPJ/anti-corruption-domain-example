package uk.co.mettle.common;

import lombok.Value;

import java.util.UUID;

@Value
public class TakePaymentRequest {
    UUID paymentId;
    String fraudIndicator;
    int aNumber;
}
