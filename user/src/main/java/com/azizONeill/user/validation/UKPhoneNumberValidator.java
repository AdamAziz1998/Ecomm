package com.azizONeill.user.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class UKPhoneNumberValidator implements ConstraintValidator<UKPhoneNumber, String> {

    private static final String UK_PHONE_NUMBER_PATTERN =
            "^\\+44\\s?\\(?0\\)?\\s?[1-9]\\d{1,4}\\s?\\d{6,8}$";

    @Override
    public void initialize(UKPhoneNumber constraintAnnotation) {
        // No initialization needed
    }

    @Override
    public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
        if (phoneNumber == null) {
            return true;  // null values are considered valid
        }
        return Pattern.matches(UK_PHONE_NUMBER_PATTERN, phoneNumber);
    }
}
