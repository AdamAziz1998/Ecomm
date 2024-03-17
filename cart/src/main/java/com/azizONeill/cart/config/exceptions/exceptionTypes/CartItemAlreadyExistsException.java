package com.azizONeill.cart.config.exceptions.exceptionTypes;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class CartItemAlreadyExistsException extends RuntimeException{

    private String errorMessage;
}
