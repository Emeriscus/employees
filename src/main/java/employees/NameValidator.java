package employees;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

// itt a Name, az annotáció neve, és a String az, amire rá akarjuk tenni a validációt
public class NameValidator implements ConstraintValidator<Name, String> {

    private int maxLength;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value != null &&
                !value.isBlank() &&
                value.length() > 2 &&
                value.length() <= maxLength &&
                Character.isUpperCase(value.charAt(0));
    }

    @Override
    public void initialize(Name constraintAnnotation) {
        maxLength = constraintAnnotation.maxLength();
    }
}
