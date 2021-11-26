package by.bsuir.intelligentscheduler.dao;

import by.bsuir.intelligentscheduler.entity.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskDao {

    public List<Task> getAllByUserId(long userId) {
        return new ArrayList<>();
    }

    public Optional<Task> getById(long id) {
        return Optional.empty();
    }

    public void create(Task task) {

    }

    public void delete(long id) {

    }
}
