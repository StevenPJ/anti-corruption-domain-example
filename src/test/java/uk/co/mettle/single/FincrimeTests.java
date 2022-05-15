package uk.co.mettle.single;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import uk.co.mettle.common.FincrimeClient;
import uk.co.mettle.common.TakePaymentRequest;

import java.util.UUID;

public class FincrimeTests {

    private final FincrimeClient fincrimeClient = Mockito.mock(FincrimeClient.class);
    private final PaymentOrchestrator paymentService = new PaymentOrchestrator(new InMemoryRepository(), fincrimeClient);

    @Test
    void receivedFraudCheckerInSyncProcessing() {
        //given
        var aPayment = UUID.randomUUID();

        // when
        paymentService.takePayment(new TakePaymentRequest(aPayment, "4Drugz", 1));

        // then
        Mockito.verify(fincrimeClient).check(new FincrimeClient.CrimeCheckRequest(aPayment, "4Drugz", 1));
    }

    @Test
    void receivedFraudCheckerInAsyncProcessing() {
        //given
        var aPayment = UUID.randomUUID();

        // when
        paymentService.takePayment(new TakePaymentRequest(aPayment, "4Drugz", 1));

        // then
        assert EventsPublisher.getInstance().hasPublished(new PaymentEvent(aPayment, "4Drugz", true, 1));
    }
}
