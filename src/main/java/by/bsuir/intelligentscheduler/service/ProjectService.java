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

    /**
     * Returns {@link Project} objects from a database without any filtering of a particular {@link User}.
     *
     * @param userId - {@link User} id
     * @param page  - the number of a page to show.
     * @param pageSize - the number of {@link Project} objects on a page.
     * @return a {@link List} of {@link Project} objects.
     */
    public List<Project> getAllByUserId(long userId, int page, int pageSize) {
        checkPaginationParameters(page, pageSize);
        List<Project> projects = projectDao.getAllByUserId(userId);

        return getPaginated(projects, pageSize, (page-1)*pageSize);
    }

    /**
     * Returns a {@link Project} object from a database by its id or throws {@link ResourceNotFoundException}
     * if nothing is retrieved from a database.
     *
     * @param id - the {@link Project} object's id that is to be retrieved from a database.
     * @return {@link Project} object.
     */
    public Project getById(long id) {
        Optional<Project> optionalOrder = projectDao.getById(id);
        return optionalOrder.orElseThrow(() -> new ResourceNotFoundException());
    }

    /**
     * Creates a {@link Project} object in a database or throws {@link RequiredFieldsMissingException} if some fields
     * required for creation are missing.
     *
     * @param project - the {@link Project} object that is to be created in a database.
     */
    public void create(Project project) {
        projectValidator.validateForCreation(project);
        projectDao.create(project);
    }

    /**
     * Deletes a {@link Project} object in a database by its id or throws {@link ResourceNotFoundException} if the object
     * with such id doesn't exist.
     *
     * @param id - the {@link Project} object's id that is to be deleted in a database.
     */
    public void delete(long id) {
        Optional<Project> optionalProject = projectDao.getById(id);
        Project project = optionalProject.orElseThrow(() -> new ResourceNotFoundException());

        projectDao.delete(project.getId());
    }
}
