package com.rahul.notification.kafka.order;

public record Customer(
        String id,
        String firstname,
        String lastname,
        String email
) {

}