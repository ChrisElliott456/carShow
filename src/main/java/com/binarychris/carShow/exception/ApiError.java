package com.binarychris.carShow.exception;

import java.time.LocalDateTime;

public record ApiError(
        String path,
        String message,
        int statusCode,
        LocalDateTime timestamp
        ) {

}
// Record is immutable data, use to have to create class without setters, but Java built in Record. Use path instead of get.