package com.azizONeill.user.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UKPhoneNumberValidator.class)
@Documented
public @interface UKPhoneNumber {
    String message() default "Invalid UK phone number format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
