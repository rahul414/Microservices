package com.rahul.order.kafka;

import com.rahul.order.customer.CustomerResponse;
import com.rahul.order.model.PaymentMethod;
import com.rahul.order.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation (
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products

) {
}
