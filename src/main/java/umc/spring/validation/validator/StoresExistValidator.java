package umc.spring.validation.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.domain.Store;
import umc.spring.service.StoreService.StoreQueryService;
import umc.spring.validation.annotation.ExistStores;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class StoresExistValidator implements ConstraintValidator<ExistStores, Long> {

    private final StoreQueryService storeQueryService;

    @Override
    public void initialize(ExistStores constraintAnnotation) {
        System.out.println("우오아앙11");
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        System.out.println("우오아앙22");
        Optional<Store> target = storeQueryService.findStore(value);

        if (target.isEmpty()){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.STORE_NOT_FOUND.toString()).addConstraintViolation();
            return false;
        }
        return true;
    }
}