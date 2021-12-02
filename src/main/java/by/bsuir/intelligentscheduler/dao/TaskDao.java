package by.bsuir.intelligentscheduler.dao;

import by.bsuir.intelligentscheduler.entity.Task;
import org.jmantic.api.context.DefaultScContext;
import org.jmantic.scmemory.model.ScMemory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskDao {

    private final ScMemory scMemory;
    private final DefaultScContext scContext;

    @Autowired
    public TaskDao(ScMemory scMemory, DefaultScContext scContext) {
        this.scMemory = scMemory;
        this.scContext = scContext;
    }

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
