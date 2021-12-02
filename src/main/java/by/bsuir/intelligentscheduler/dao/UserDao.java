package by.bsuir.intelligentscheduler.dao;

import by.bsuir.intelligentscheduler.entity.User;
import org.jmantic.api.context.DefaultScContext;
import org.jmantic.scmemory.model.ScMemory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao {

    private final ScMemory scMemory;
    private final DefaultScContext scContext;

    @Autowired
    public UserDao(ScMemory scMemory, DefaultScContext scContext) {
        this.scMemory = scMemory;
        this.scContext = scContext;
    }

    public List<User> getAll() {
        return new ArrayList<>();
    }

    public Optional<User> getById(long id) {
        return Optional.empty();
    }

    public Optional<User> getByLogin(String login) {
        return Optional.empty();
    }

    public void create(User user) {

    }

    private void createLoginForUser(String login) {

    }

    private void createPasswordForUser(String password) {

    }

    public void delete(long id) {

    }
}
