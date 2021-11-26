package by.bsuir.intelligentscheduler.validator;

import by.bsuir.intelligentscheduler.entity.User;
import by.bsuir.intelligentscheduler.exception.RequiredFieldsMissingException;
import org.apache.commons.lang3.StringUtils;

public class UserValidator {

    public void validateForCreation(User user) {
        validateLogin(user.getLogin());
        validatePassword(user.getPassword());
    }

    private void validateLogin(String login) {
        if (StringUtils.isBlank(login)) {
            throw new RequiredFieldsMissingException();
        }
    }

    private void validatePassword(String password) {
        if (StringUtils.isBlank(password)) {
            throw new RequiredFieldsMissingException();
        }
    }
}
