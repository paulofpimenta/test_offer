package com.api.demo.validation;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.SneakyThrows;


public class PhoneNumberIsFrenchValidator implements ConstraintValidator<PhoneNumberIsFrench, String> {

    private String message;

    @Override
    public void initialize(final PhoneNumberIsFrench phoneNumberIsFrench) {
        this.message = phoneNumberIsFrench.message();
    }

    @Override
    public boolean isValid(String phoneNumber,
                           ConstraintValidatorContext cxt) {
        if (!(phoneNumber == null)) {
                PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
                Phonenumber.PhoneNumber number = null;
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
