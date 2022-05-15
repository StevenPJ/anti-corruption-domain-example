package uk.co.mettle.multiple.common;


import uk.co.mettle.multiple.fincrime.PaymentEventForFincrime;

import java.util.ArrayList;
import java.util.List;

public class EventsPublisher {

    private static final EventsPublisher instance = new EventsPublisher();

    public static EventsPublisher getInstance() {
        return instance;
    }

    List<PaymentEventForFincrime> events = new ArrayList<>();

    private EventsPublisher() {}

    public void publish(PaymentEventForFincrime paymentEvent) {
        this.events.add(paymentEvent);
    }

    public boolean hasPublished(PaymentEventForFincrime event) {
        return events.contains(event);
    }
}
