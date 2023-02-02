package com.api.demo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;


@Slf4j
public class Under18Validator implements ConstraintValidator<Under18, String> {

    private String message;

    @Override
    public void initialize(final Under18 under18) {
        this.message = under18.message();
    }

    @Override
    public boolean isValid(String birthDate,
                           ConstraintValidatorContext cxt) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        simpleDateFormat.setLenient(false);
        Date dateNow = Date.from(Instant.now());

        if (!(birthDate == null)) {
            try
            {
                Date birthDateAsDate = simpleDateFormat.parse(birthDate);
                long deltaTime = dateNow.getTime() - birthDateAsDate.getTime();
                int age = (int) (deltaTime / (1000l * 60 * 60 * 24 * 365));

                if (age >= 18) {
                    log.debug("Birth date " + birthDate + " is valid date format. User is over 18");
                    return true;
                } else {
                    log.debug("Birth date " + birthDate + " is valid date format, but user is only " + age + " years old");
                    return false;
                }
            }
            /* Date format is invalid */
            catch (ParseException e)
            {
                System.out.println(birthDate + " is Invalid Date format");
                return false;
            }
        }
        // Validate but leave it to @Null validator handler
        return true;
    }
}