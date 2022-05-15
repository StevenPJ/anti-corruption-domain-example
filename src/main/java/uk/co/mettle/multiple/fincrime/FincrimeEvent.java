package uk.co.mettle.multiple.fincrime;

import lombok.Value;

import java.util.UUID;

@Value
public class FincrimeEvent {
    UUID paymentId;
    String crimeIndicator;
    int aNumber;
}
