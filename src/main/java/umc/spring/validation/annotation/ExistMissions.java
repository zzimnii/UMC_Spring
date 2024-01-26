package umc.spring.validation.annotation;

import umc.spring.validation.validator.MissionExistValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MissionExistValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistMissions {
    String message() default "해당하는 미션이 존재하지 않습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload> [] payload() default {};
}
