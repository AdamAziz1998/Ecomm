package com.azizONeill.cart.config.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class ApiExceptions {

    private final Set<String> messages;
    private final Throwable throwable;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timeStamp;
}
