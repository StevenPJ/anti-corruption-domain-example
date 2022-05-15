package uk.co.mettle.multiple.payment;


import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Builder(toBuilder = true)
@Value
public class Payment {
    UUID id;
    boolean completed;

    public Payment takePayment() {
        return this.toBuilder().completed(true).build();
    }
}