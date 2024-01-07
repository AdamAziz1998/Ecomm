package com.azizONeill.user.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class EmailAddressValidator implements ConstraintValidator<EmailAddress, String> {

    private static final String UK_EMAIL_ADDRESS_PATTERN =
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";

    @Override
    public void initialize(EmailAddress constraintAnnotation) {
        // No initialization needed
    }

    @Override
    public boolean isValid(String emailAddress, ConstraintValidatorContext context) {
        if (emailAddress == null) {
            return true;  // null values are considered valid
        }
        return Pattern.matches(UK_EMAIL_ADDRESS_PATTERN, emailAddress);
    }
}
