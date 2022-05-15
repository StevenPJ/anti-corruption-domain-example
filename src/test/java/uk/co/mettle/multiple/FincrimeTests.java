package uk.co.mettle.multiple;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import uk.co.mettle.common.FincrimeClient;
import uk.co.mettle.common.TakePaymentRequest;
import uk.co.mettle.multiple.common.EventsPublisher;
import uk.co.mettle.multiple.common.InMemoryRepository;
import uk.co.mettle.multiple.fincrime.FincrimeService;
import uk.co.mettle.multiple.fincrime.PaymentEventForFincrime;

import java.util.UUID;

public class FincrimeTests {

    private final FincrimeClient fincrimeClient = Mockito.mock(FincrimeClient.class);
    private final InMemoryRepository inMemoryRepository = new InMemoryRepository();
    private final PaymentOrchestrator paymentService = new PaymentOrchestrator(inMemoryRepository, new FincrimeService(fincrimeClient,inMemoryRepository));

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
        assert EventsPublisher.getInstance().hasPublished(new PaymentEventForFincrime(aPayment, "4Drugz", true, 1));
    }
}
