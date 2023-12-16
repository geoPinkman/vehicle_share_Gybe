package com.greenGekko.validator;


import com.greenGekko.dto.UserRegistrationDto;
import com.greenGekko.models.JUser;
import com.greenGekko.servicies.user_service.UsersService;
import com.greenGekko.utils.ValidationEmail;
import com.greenGekko.utils.ValidationPassword;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
@AllArgsConstructor
public class UserValidator implements Validator {

    private final UsersService usersService;

    @Override
    public boolean supports(Class<?> clazz) {
        return JUser.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
         UserRegistrationDto user = (UserRegistrationDto) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Required");

        if (!ValidationEmail.isValid(user.getEmail())) {
            errors.rejectValue("email", "User.email.format");
        }

        if(usersService.isUser(user.getEmail())) {
            errors.rejectValue("email", "Duplicate.user.email");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userPassword", "Required");

        if (!ValidationPassword.isValid(user.getUserPassword())) {
            errors.rejectValue("userPassword", "User.password.format");
        }
        if (!user.getConfirmPassword().equals(user.getUserPassword())) {
            errors.rejectValue("confirmPassword", "Different.confirm.password");

        }
    }
}
