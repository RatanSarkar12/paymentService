package com.service.paymentService.paymentGateWays;

import com.stripe.exception.StripeException;

public interface PaymentGateWay {

    String generatePaymentLink(Long amount) throws StripeException;
}
