package uk.co.mettle.multiple.payment;

import java.util.UUID;

public interface PaymentRepository {

    Payment findBy(UUID paymentId);

    void save(Payment payment);
}
