package com.azizONeill.user.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailAddressValidator.class)
@Documented
public @interface EmailAddress {
    String message() default "Invalid UK email address format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
