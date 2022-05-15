package uk.co.mettle.single;

import lombok.Value;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Value
class InMemoryRepository implements PaymentRepository {

    Map<UUID, Payment> payments = new HashMap<>();
    EventsPublisher eventsPublisher = EventsPublisher.getInstance();

    @Override
    public Payment findBy(UUID paymentId) {
        return Optional.ofNullable(payments.get(paymentId))
                .orElseGet(() -> new Payment(paymentId, null, false));
    }

    @Override
    public void save(Payment payment) {
        payments.put(payment.getId(),payment);
        eventsPublisher.publish(new PaymentEvent(payment.getId(), payment.getFraudIndicator(), payment.isCompleted()));
    }
}
