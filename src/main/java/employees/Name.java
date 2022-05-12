package employees;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)             //futás köben feldolgozható legyen
@Target({ElementType.FIELD})                    // mire lehet rárakni, itt field-re
@Constraint(validatedBy = NameValidator.class)  //itt adjuk meg az osztályt, ami a validálást végzi: NameValidator
public @interface Name {

    // kötelezően megadandó dolog, a message:
    String message() default "Invalid name";

    // kötelezően megadandó dolog, egy Class típusú groups:
    Class<?>[] groups() default {};

    // kötelezően megadandó dolog, egy payload:
    Class<? extends Payload>[] payload() default {};

    // saját paraméterek, alapértelmezett értékkel
    int maxLength() default 50;
}
