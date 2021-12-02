package by.bsuir.intelligentscheduler.dao;

import by.bsuir.intelligentscheduler.entity.Project;
import org.jmantic.api.context.DefaultScContext;
import org.jmantic.scmemory.model.ScMemory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectDao {

    private final ScMemory scMemory;
    private final DefaultScContext scContext;

    @Autowired
    public ProjectDao(ScMemory scMemory, DefaultScContext scContext) {
        this.scMemory = scMemory;
        this.scContext = scContext;
    }

    public List<Project> getAllByUserId(long userId) {
        return new ArrayList<>();
    }

    public Optional<Project> getById(long id) {
        return Optional.empty();
    }

    public void create(Project project) {

    }

    public void delete(long id) {

    }
}
