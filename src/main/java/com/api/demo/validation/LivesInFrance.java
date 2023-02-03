package com.api.demo.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({ FIELD,TYPE,PARAMETER,METHOD })
@Retention(RUNTIME)
@Constraint(validatedBy = LivesInFranceValidator.class)
public @interface LivesInFrance {
    String message() default "The user's residenWWce is not France";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

