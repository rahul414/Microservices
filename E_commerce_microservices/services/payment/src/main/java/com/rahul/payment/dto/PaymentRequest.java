package com.rahul.payment.dto;

import com.rahul.payment.model.Customer;
import com.rahul.payment.model.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        Integer id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        Customer customer
) {
}
