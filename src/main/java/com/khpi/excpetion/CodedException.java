package com.khpi.excpetion;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@Builder
public class CodedException extends RuntimeException {
    private static final Long serialVersionUID = 2962144070439177464L;
    private final int httpStatus;

    public CodedException(final int statusCode, final String message) {
        super(message);
        this.httpStatus = statusCode;
    }

    public CodedException(final int statusCode, final Throwable cause) {
        super(cause);
        this.httpStatus = statusCode;
    }

    public CodedException(final int statusCode, final String message, final Throwable cause) {
        super(message, cause);
        this.httpStatus = statusCode;
    }
}
