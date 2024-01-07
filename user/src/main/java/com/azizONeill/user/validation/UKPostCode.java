package com.azizONeill.user.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UKPostCodeValidator.class)
@Documented
public @interface UKPostCode {
    String message() default "Invalid UK post code format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
