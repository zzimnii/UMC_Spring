package umc.spring.validation.annotation;

import umc.spring.validation.validator.RegionsExistValidator;
import umc.spring.validation.validator.StoresExistValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RegionsExistValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistRegion {

    String message() default "해당하는 지역이 존재하지 않습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload> [] payload() default {};
}
