package com.rahul.product.handler;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {

}
