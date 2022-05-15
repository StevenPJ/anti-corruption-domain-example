package uk.co.mettle.single;

import java.util.ArrayList;
import java.util.List;

class EventsPublisher {

    private static final EventsPublisher instance = new EventsPublisher();

    public static EventsPublisher getInstance() {
        return instance;
    }

    List<PaymentEvent> events = new ArrayList<>();

    private EventsPublisher() {}

    public void publish(PaymentEvent paymentEvent) {
        this.events.add(paymentEvent);
    }

    public boolean hasPublished(PaymentEvent event) {
        return events.contains(event);
    }

}
