package com.rahul.customer.handler;

import java.util.Map;

public record ErrorResponse(
        Map<Object, Object> errors
) {

}
