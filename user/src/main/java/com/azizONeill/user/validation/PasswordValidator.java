package com.azizONeill.user.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {

    private static final int MIN_LENGTH = 8;

    @Override
    public void initialize(Password constraintAnnotation) {
        // No initialization needed
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null) {
            return false;  // null values are considered invalid
        }

        // Password must be at least 8 characters long
        if (password.length() < MIN_LENGTH) {
            return false;
        }

        // Password must contain at least one uppercase letter
        if (!containsUppercase(password)) {
            return false;
        }

        // Password must contain at least one lowercase letter
        if (!containsLowercase(password)) {
            return false;
        }

        // Password must contain at least one digit
        if (!containsDigit(password)) {
            return false;
        }

        // Password must contain at least one special character
        if (!containsSpecialCharacter(password)) {
            return false;
        }

        return true;
    }

    private boolean containsUppercase(String password) {
        return password.matches(".*[A-Z].*");
    }

    private boolean containsLowercase(String password) {
        return password.matches(".*[a-z].*");
    }

    private boolean containsDigit(String password) {
        return password.matches(".*\\d.*");
    }

    private boolean containsSpecialCharacter(String password) {
        return password.matches(".*[^a-zA-Z0-9].*");
    }
}
