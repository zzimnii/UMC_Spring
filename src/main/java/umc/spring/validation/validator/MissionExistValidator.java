package umc.spring.validation.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.domain.Mission;
import umc.spring.repository.MissionRepository;
import umc.spring.validation.annotation.ExistMissions;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MissionExistValidator implements ConstraintValidator<ExistMissions, List<Long>> {

    private final MissionRepository missionRepository;

    @Override
    public void initialize(ExistMissions constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<Long> values, ConstraintValidatorContext context) {
        boolean isValid = values.stream()
                .allMatch(value -> missionRepository.existsById(value));
        if(!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_NOT_FOUND.toString()).addConstraintViolation();
        }

        return isValid;
    }
}
