package com.api.demo.validation;



import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = LivesInFranceValidator.class)
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.ANNOTATION_TYPE,ElementType.CONSTRUCTOR,ElementType.PARAMETER,ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LivesInFrance {
    String message() default "The user residence is not France";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

