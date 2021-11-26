package by.bsuir.intelligentscheduler.dao;

import by.bsuir.intelligentscheduler.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao {

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

    public void delete(long id) {

    }
}
