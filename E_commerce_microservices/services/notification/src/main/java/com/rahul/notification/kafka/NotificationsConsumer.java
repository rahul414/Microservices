package com.rahul.notification.kafka;

import com.rahul.notification.kafka.order.OrderConfirmation;
import com.rahul.notification.kafka.payment.PaymentConfirmation;
import com.rahul.notification.model.Notification;
import com.rahul.notification.model.NotificationType;
import com.rahul.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationsConsumer {

    private final NotificationRepository notificationRepository;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotification(PaymentConfirmation paymentConfirmation){
        log.info(String.format("Consuming the message from payment-topic Topic:: %s", paymentConfirmation));
        notificationRepository.save(
                Notification.builder()
                        .type(NotificationType.PAYMENT_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .paymentConfirmation(paymentConfirmation)
                        .build());

    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotification(OrderConfirmation orderConfirmation){
        log.info(String.format("Consuming the message from order-topic Topic:: %s", orderConfirmation));
        notificationRepository.save(
                Notification.builder()
                        .type(NotificationType.ORDER_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .orderConfirmation(orderConfirmation)
                        .build());

    }
}
