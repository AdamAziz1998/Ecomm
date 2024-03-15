package com.azizONeill.cart.config.exceptions.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class ObjectNotValidException extends RuntimeException {

    private Set<String> errorMessages;
}
