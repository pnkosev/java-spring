package app.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CapitalLetterValidator implements ConstraintValidator<CapitalLetter, String> {

   @Override
   public boolean isValid(String name, ConstraintValidatorContext context) {
      return name.matches("\\b[A-Z].*?\\b");
   }
}
