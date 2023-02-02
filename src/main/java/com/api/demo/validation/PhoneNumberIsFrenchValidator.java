package com.api.demo.validation;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class PhoneNumberIsFrenchValidator implements ConstraintValidator<PhoneNumberIsFrench, String> {

    private String message;

    @Override
    public void initialize(final PhoneNumberIsFrench constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String phoneNumber,
                           ConstraintValidatorContext cxt) {
        if (!(phoneNumber == null)) {
            try {
                PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
                Phonenumber.PhoneNumber number = phoneUtil.parse(phoneNumber, "FR");
                return phoneUtil.isValidNumberForRegion(number, "FR");
            } catch (NumberParseException e) {
                throw new RuntimeException("Could not validate phone number: " + e);
            }
        } else {
            return true;
        }
    }
}
