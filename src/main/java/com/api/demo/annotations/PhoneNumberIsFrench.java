package com.api.demo.annotations;

import com.api.demo.validators.PhoneNumberIsFrenchValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Documented
@Target({ FIELD,TYPE,PARAMETER,METHOD })
@Retention(RUNTIME)
@Constraint(validatedBy = PhoneNumberIsFrenchValidator.class)
public @interface PhoneNumberIsFrench {
    String message() default "User's phone number is not a valid French phone number";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}