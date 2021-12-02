package by.bsuir.intelligentscheduler.service;

import by.bsuir.intelligentscheduler.dao.UserDao;
import by.bsuir.intelligentscheduler.entity.User;
import by.bsuir.intelligentscheduler.exception.RequiredFieldsMissingException;
import by.bsuir.intelligentscheduler.exception.ResourceAlreadyExistsException;
import by.bsuir.intelligentscheduler.exception.ResourceNotFoundException;
import by.bsuir.intelligentscheduler.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService extends AbstractService<User> {

    private final UserDao userDao;
    private final UserValidator userValidator;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDao userDao, UserValidator userValidator, BCryptPasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.userValidator = userValidator;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAll(int page, int pageSize) {
        checkPaginationParameters(page, pageSize);
        List<User> users = userDao.getAll();

        return getPaginated(users, pageSize, (page-1)*pageSize);
    }

    public User getById(long id) {
        Optional<User> optionalUser = userDao.getById(id);
        return optionalUser.orElseThrow(() -> new ResourceNotFoundException());
    }

    public User getByLogin(String login) {
        Optional<User> optionalUser = userDao.getByLogin(login);
        return optionalUser.orElseThrow(() -> new ResourceNotFoundException());
    }

    public void create(User user) {
        userValidator.validateForCreation(user);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userDao.create(user);
    }

    public void delete(long id) {
        Optional<User> optionalUser = userDao.getById(id);
        User user = optionalUser.orElseThrow(() -> new ResourceNotFoundException());

        userDao.delete(user.getId());
    }
}
