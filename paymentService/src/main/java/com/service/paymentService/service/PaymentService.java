package com.service.paymentService.service;

import com.service.paymentService.paymentGateWays.stripe.StripePaymentGateWay;
import com.stripe.exception.StripeException;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private StripePaymentGateWay stripePaymentGateWay;

    public PaymentService(StripePaymentGateWay stripePaymentGateWay){
        this.stripePaymentGateWay=stripePaymentGateWay;
    }

    public String createPaymentLink(Long orderId) throws StripeException {
               return stripePaymentGateWay.generatePaymentLink(1000L);
        //ask email of customer
        //phonenumber of customer
        //long amount=
        //how payment service will get info of order service
        //Order order =restClient.get(localhost:9000/order/orderId
        //long userId = order.geUserId
        //User user =  restClient.get(localhost:9000/users/{userId}\
        //string emailOfCustomer = user.getmail
        //String phoneNUMBER=uer.getPhoneNumber
        //long amount = order.getTotalAmoount


    }
}
