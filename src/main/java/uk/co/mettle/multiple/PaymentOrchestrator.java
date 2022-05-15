package uk.co.mettle.multiple;

import lombok.RequiredArgsConstructor;
import uk.co.mettle.common.TakePaymentRequest;
import uk.co.mettle.multiple.fincrime.Fincrime;
import uk.co.mettle.multiple.payment.PaymentRepository;

@RequiredArgsConstructor
class PaymentOrchestrator {

    private final PaymentRepository paymentRepository;
    private final Fincrime fincrime;

    public void takePayment(TakePaymentRequest request) {
        var payment = paymentRepository.findBy(request.getPaymentId());
        fincrime.check(new Fincrime.PaymentCrimeRequest(request.getPaymentId(), request.getFraudIndicator(), request.getANumber()));
        paymentRepository.save(payment.takePayment());
    }
}
