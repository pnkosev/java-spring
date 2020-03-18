package app.utils.validators;

import app.utils.annotations.DateBeforeToday;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class DateBeforeTodayValidator implements ConstraintValidator<DateBeforeToday, LocalDate> {
   public boolean isValid(LocalDate date, ConstraintValidatorContext context) {
      LocalDate today = LocalDate.now();
      return date != null && date.isBefore(today);
   }
}
