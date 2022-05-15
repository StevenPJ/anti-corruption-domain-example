package uk.co.mettle.single;

import java.util.UUID;

interface PaymentRepository {

    Payment findBy(UUID paymentId);

    void save(Payment payment);
}
