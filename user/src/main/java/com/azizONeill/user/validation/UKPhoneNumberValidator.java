package com.azizONeill.user.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class UKPhoneNumberValidator implements ConstraintValidator<UKPhoneNumber, String> {

    private static final String UK_PHONE_NUMBER_PATTERN =
            "^(((\\+44\\s?\\d{4}|\\(?0\\d{4}\\)?)\\s?\\d{3}\\s?\\d{3})|((\\+44\\s?\\d{3}|\\(?0\\d{3}\\)?)\\s?\\d{3}\\s?\\d{4})|((\\+44\\s?\\d{2}|\\(?0\\d{2}\\)?)\\s?\\d{4}\\s?\\d{4}))(\\s?#(\\d{4}|\\d{3}))?$";

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
