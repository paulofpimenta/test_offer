package com.api.demo.validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LivesInFranceValidator implements ConstraintValidator<LivesInFrance, String> {

    @Override
    public void initialize(LivesInFrance livesInFrance) {
    }

    @Override
    public boolean isValid(String countryOfResidence, ConstraintValidatorContext constraintValidatorContext) {
        if (!(countryOfResidence == null)) {
            return countryOfResidence.matches("FR|France|fr|Fr|fR");
        } else {
            return true;
        }
    }
}
