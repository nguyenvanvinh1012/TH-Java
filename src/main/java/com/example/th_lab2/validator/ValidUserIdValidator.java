package com.example.th_lab2.validator;

import com.example.th_lab2.enity.User;
import com.example.th_lab2.validator.annotation.ValidUserId;
import com.example.th_lab2.validator.annotation.ValidUsername;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidUserIdValidator implements ConstraintValidator<ValidUserId, User> {
    @Override
    public boolean isValid(User user, ConstraintValidatorContext context) {
        if(user == null)
            return true;
        return user.getId() != null;
    }
}
