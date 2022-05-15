package uk.co.mettle.single;

import lombok.RequiredArgsConstructor;
import uk.co.mettle.common.FincrimeClient;
import uk.co.mettle.common.TakePaymentRequest;

@RequiredArgsConstructor
class PaymentOrchestrator {

    private final PaymentRepository paymentRepository;
    private final FincrimeClient fincrimeClient;

    public void takePayment(TakePaymentRequest request) {
        var payment = paymentRepository.findBy(request.getPaymentId());
        payment = payment.takePayment(request);
        fincrimeClient.check(new FincrimeClient.CrimeCheckRequest(request.getPaymentId(), request.getFraudIndicator()));
        paymentRepository.save(payment);
    }
}
