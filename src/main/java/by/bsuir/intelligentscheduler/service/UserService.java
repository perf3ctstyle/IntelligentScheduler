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

    /**
     * Returns {@link User} objects from a database without any filtering.
     *
     * @param page  - the number of a page to show.
     * @param pageSize - the number of {@link User} objects on a page.
     * @return a {@link List} of {@link User} objects.
     */
    public List<User> getAll(int page, int pageSize) {
        checkPaginationParameters(page, pageSize);
        List<User> users = userDao.getAll();

        return getPaginated(users, pageSize, (page-1)*pageSize);
    }

    /**
     * Returns a {@link User} object from a database by its id or throws {@link ResourceNotFoundException}
     * if nothing is retrieved from a database.
     *
     * @param id - the {@link User} object's id that is to be retrieved from a database.
     * @return {@link User} object.
     */
    public User getById(long id) {
        Optional<User> optionalUser = userDao.getById(id);
        return optionalUser.orElseThrow(() -> new ResourceNotFoundException());
    }

    /**
     * Returns a {@link User} object from a database by its login or throws {@link ResourceNotFoundException}
     * if nothing is retrieved from a database.
     *
     * @param login - the {@link User} object's login that is to be retrieved from a database.
     * @return {@link User} object.
     */
    public User getByLogin(String login) {
        Optional<User> optionalUser = userDao.getByLogin(login);
        return optionalUser.orElseThrow(() -> new ResourceNotFoundException());
    }

    /**
     * Creates a {@link User} object in a database or throws {@link RequiredFieldsMissingException} if some fields
     * required for creation are missing or {@link ResourceAlreadyExistsException} if the User with the same login already exists.
     *
     * @param user - the {@link User} object that is to be created in a database.
     */
    public void create(User user) {
        userValidator.validateForCreation(user);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userDao.create(user);
    }

    /**
     * Deletes a {@link User} object in a database by its id or throws {@link ResourceNotFoundException} if the object
     * with such id doesn't exist.
     *
     * @param id - the {@link User} object's id that is to be deleted in a database.
     */
    public void delete(long id) {
        Optional<User> optionalUser = userDao.getById(id);
        User user = optionalUser.orElseThrow(() -> new ResourceNotFoundException());

        userDao.delete(user.getId());
    }
}
