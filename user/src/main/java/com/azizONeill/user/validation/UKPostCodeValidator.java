package com.azizONeill.user.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class UKPostCodeValidator implements ConstraintValidator<UKPostCode, String> {

    private static final String UK_POST_CODE_PATTERN =
            "^[A-Z]{1,2}[0-9R][0-9A-Z]?\\s?[0-9][A-Z]{2}$";

    @Override
    public void initialize(UKPostCode constraintAnnotation) {
        // No initialization needed
    }

    @Override
    public boolean isValid(String postCode, ConstraintValidatorContext context) {
        if (postCode == null) {
            return true;  // null values are considered valid
        }
        return Pattern.matches(UK_POST_CODE_PATTERN, postCode);
    }
}
