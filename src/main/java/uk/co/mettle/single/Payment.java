package uk.co.mettle.single;

import lombok.Builder;
import lombok.Value;
import uk.co.mettle.common.TakePaymentRequest;

import java.util.UUID;

@Builder(toBuilder = true)
@Value
class Payment {
    UUID id;
    String fraudIndicator;
    boolean completed;
    int aNumber;

    public Payment takePayment(TakePaymentRequest request) {
        return this.toBuilder()
                .fraudIndicator(request.getFraudIndicator())
                .completed(true)
                .aNumber(request.getANumber())
                .build();
    }
}
