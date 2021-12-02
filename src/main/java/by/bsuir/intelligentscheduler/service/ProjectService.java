package by.bsuir.intelligentscheduler.service;

import by.bsuir.intelligentscheduler.dao.ProjectDao;
import by.bsuir.intelligentscheduler.entity.User;
import by.bsuir.intelligentscheduler.entity.Project;
import by.bsuir.intelligentscheduler.exception.ResourceAlreadyExistsException;
import by.bsuir.intelligentscheduler.exception.ResourceNotFoundException;
import by.bsuir.intelligentscheduler.exception.RequiredFieldsMissingException;
import by.bsuir.intelligentscheduler.validator.ProjectValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService extends AbstractService<Project> {

    private final ProjectDao projectDao;
    private final ProjectValidator projectValidator;

    @Autowired
    public ProjectService(ProjectDao projectDao, ProjectValidator projectValidator) {
        this.projectDao = projectDao;
        this.projectValidator = projectValidator;
    }

    public List<Project> getAllByUserId(long userId, int page, int pageSize) {
        checkPaginationParameters(page, pageSize);
        List<Project> projects = projectDao.getAllByUserId(userId);

        return getPaginated(projects, pageSize, (page-1)*pageSize);
    }

    public Project getById(long id) {
        Optional<Project> optionalProject = projectDao.getById(id);
        return optionalProject.orElseThrow(() -> new ResourceNotFoundException());
    }

    public void create(Project project) {
        projectValidator.validateForCreation(project);
        projectDao.create(project);
    }

    public void delete(long id) {
        Optional<Project> optionalProject = projectDao.getById(id);
        Project project = optionalProject.orElseThrow(() -> new ResourceNotFoundException());

        projectDao.delete(project.getId());
    }
}
