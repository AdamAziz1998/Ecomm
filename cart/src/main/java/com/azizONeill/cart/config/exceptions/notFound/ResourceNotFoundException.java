package com.azizONeill.cart.config.exceptions.notFound;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class ResourceNotFoundException extends RuntimeException {

    private String errorMessage;
}