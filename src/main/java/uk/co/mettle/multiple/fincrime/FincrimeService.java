package uk.co.mettle.multiple.fincrime;

import lombok.RequiredArgsConstructor;
import uk.co.mettle.common.FincrimeClient;

@RequiredArgsConstructor
public class FincrimeService implements Fincrime {

    private final FincrimeClient fincrimeClient;
    private final FincrimeRepository fincrimeRepository;

    @Override
    public void check(PaymentCrimeRequest request) {
        fincrimeClient.check(new FincrimeClient.CrimeCheckRequest(request.getPaymentId(), request.getFraudIndiciator()));
        fincrimeRepository.save(new FincrimeEvent(request.getPaymentId(), request.getFraudIndiciator()));
    }
}
