package uk.co.mettle.multiple.common;

import lombok.Value;
import uk.co.mettle.multiple.fincrime.FincrimeEvent;
import uk.co.mettle.multiple.fincrime.FincrimeRepository;
import uk.co.mettle.multiple.fincrime.PaymentEventForFincrime;
import uk.co.mettle.multiple.payment.Payment;
import uk.co.mettle.multiple.payment.PaymentRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Value
public class InMemoryRepository implements PaymentRepository, FincrimeRepository {

    Map<UUID, Payment> payments = new HashMap<>();
    Map<UUID, FincrimeEvent> cacheOfEvents = new HashMap<>();
    EventsPublisher eventsPublisher = EventsPublisher.getInstance();

    @Override
    public Payment findBy(UUID paymentId) {
        return Optional.ofNullable(payments.get(paymentId))
                .orElseGet(() -> Payment.builder().id(paymentId).completed(false).build());
    }

    @Override
    public void save(Payment payment) {
        payments.put(payment.getId(),payment);
        var fincrimeEvent = cacheOfEvents.get(payment.getId());
        eventsPublisher.publish(new PaymentEventForFincrime(payment.getId(), fincrimeEvent.getCrimeIndicator(), payment.isCompleted()));
    }

    @Override
    public void save(FincrimeEvent event) {
        cacheOfEvents.put(event.getPaymentId(), event);
    }
}
