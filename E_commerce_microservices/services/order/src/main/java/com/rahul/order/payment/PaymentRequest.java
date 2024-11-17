package com.rahul.order.payment;

import com.rahul.order.customer.CustomerResponse;
import com.rahul.order.model.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
