package com.service.paymentService.paymentGateWays.stripe;

import com.service.paymentService.paymentGateWays.PaymentGateWay;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;
import com.stripe.param.billingportal.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StripePaymentGateWay implements PaymentGateWay {
    @Value("${stripe.secret_key}")
    private String stripeSecretKey;
    //Stripe.apiKey = "sk_test_51PlRzFIUnnYVHopRAMNAo9m2p9bUwIFtCaXHzU3SH8qUAPXaHeHPVLPQta31TsGsdpJtXNBL4n7TrqSUd4NBCGtr00JDFJ6iAf";


    @Override
    public String generatePaymentLink(Long amount) throws StripeException {

        Stripe.apiKey = stripeSecretKey;

        ProductCreateParams productCreateParams =
                ProductCreateParams.builder().setName("GENERIC").build();

        Product product = Product.create(productCreateParams);

        PriceCreateParams params =
                PriceCreateParams.builder()
                        .setCurrency("usd")
                        .setUnitAmount(amount)
                        .setProduct(product.getId())
                        .build();

        Price price = Price.create(params);

        PaymentLinkCreateParams paymentLinkCreateParams =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .setAfterCompletion(
                                PaymentLinkCreateParams.AfterCompletion.builder()
                                        .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT) // Set the type of action after completion
                                        .setRedirect(
                                                PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                                                        .setUrl("http://scaler.com") // Set the URL to redirect after completion
                                                        .build()
                                        )
                                        .build()
                        )
                        .build();


        PaymentLink paymentLink = PaymentLink.create(paymentLinkCreateParams);
        return paymentLink.getUrl();
    }
}
