package com.api.demo.validation;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LivesInFranceValidator implements ConstraintValidator<LivesInFrance, String> {

    @Override
    public boolean isValid(String countryOfResidence, ConstraintValidatorContext cxt) {
        if (!(countryOfResidence == null)) {
            return countryOfResidence.matches("FR|France|fr|Fr|fR");
        } else {
            return true;
        }
    }
}
