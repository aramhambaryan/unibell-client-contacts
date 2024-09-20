package com.unibell.validator;

import com.unibell.domain.dto.request.CreateContactRequest;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ContactValidator {

    private final CustomValidator customValidator;

    public void validate(CreateContactRequest value) {
        customValidator.validateThrowing(value);
        boolean isValid = switch (value.getType()) {
            case EMAIL -> StringUtils.isNotBlank(value.getEmail()) && Objects.isNull(value.getPhone());
            case PHONE -> StringUtils.isNotBlank(value.getPhone()) && Objects.isNull(value.getEmail());
        };
        if (!isValid) {
            throw new ConstraintViolationException("legal combinations are (type=PHONE, phone=notBlank, email=null)" +
                    "and (type=EMAIL, phone=notBlank, email=null)", null);
        }
    }
}
