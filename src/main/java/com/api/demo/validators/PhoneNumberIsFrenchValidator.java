package com.api.demo.validators;

import com.api.demo.annotations.PhoneNumberIsFrench;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class PhoneNumberIsFrenchValidator implements ConstraintValidator<PhoneNumberIsFrench, String> {

    private String message;

    private PhoneNumber number;


    @Override
    public void initialize(final PhoneNumberIsFrench phoneNumberIsFrench) {
        this.message = phoneNumberIsFrench.message();
    }

    @Override
    public boolean isValid(String phoneNumber,
                           ConstraintValidatorContext cxt) {
        if (phoneNumber != null) {
                PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
            try {
                number = phoneUtil.parse(phoneNumber, "FR");
            } catch (NumberParseException e) {
                throw new RuntimeException(e);
            }
            return phoneUtil.isValidNumberForRegion(number, "FR");
        } else {
            // Phone number is not mandatory
            return true;
        }
    }
}
