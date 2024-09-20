package com.unibell.validator;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class CustomValidator {

    private final Validator validator;

    public <T> void validateThrowing(T object, Class<?>... groups) {
        Set<ConstraintViolation<T>> resultSet = validator.validate(object, groups);
        if(!resultSet.isEmpty()) {
            throw new ConstraintViolationException(resultSet);
        }
    }
}
