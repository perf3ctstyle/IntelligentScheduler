package by.bsuir.intelligentscheduler.dao;

import by.bsuir.intelligentscheduler.entity.Project;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectDao {

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
