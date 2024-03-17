package com.azizONeill.category.config.exceptions.notFound;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class ResourceNotFoundException extends RuntimeException {

    private String errorMessage;
}