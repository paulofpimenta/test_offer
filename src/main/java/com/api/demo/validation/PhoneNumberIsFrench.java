package com.api.demo.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;


@Documented
@Constraint(validatedBy = PhoneNumberIsFrenchValidator.class)
@Target( { FIELD, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumberIsFrench {
    String message() default "User's phone number is not a valid French phone number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}