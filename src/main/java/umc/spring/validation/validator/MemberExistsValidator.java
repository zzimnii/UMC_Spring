package umc.spring.validation.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.domain.Member;
import umc.spring.repository.MemberRepository;
import umc.spring.service.MemberService.MemberQueryService;
import umc.spring.validation.annotation.ExistMembers;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MemberExistsValidator implements ConstraintValidator<ExistMembers, Long> {

    private final MemberQueryService memberQueryService;

    @Override
    public void initialize(ExistMembers constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        Optional<Member> target = memberQueryService.findMember(value);

        if (target.isEmpty()){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MEMBER_NOT_FOUND.toString()).addConstraintViolation();
            return false;
        }
        return true;
    }
}