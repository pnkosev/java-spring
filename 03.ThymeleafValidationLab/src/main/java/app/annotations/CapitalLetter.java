package app.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = CapitalLetterValidator.class)
public @interface CapitalLetter {
    String message() default "Name must start with capital letter!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
