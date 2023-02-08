package com.api.demo.annotations;


import com.api.demo.validators.Under18Validator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = Under18Validator.class)
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.ANNOTATION_TYPE,ElementType.CONSTRUCTOR,ElementType.PARAMETER,ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Under18 {
    String message() default "The user's age is bellow 18";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
