package by.bsuir.intelligentscheduler.service;

import by.bsuir.intelligentscheduler.dao.TaskDao;
import by.bsuir.intelligentscheduler.entity.Task;
import by.bsuir.intelligentscheduler.entity.User;
import by.bsuir.intelligentscheduler.exception.RequiredFieldsMissingException;
import by.bsuir.intelligentscheduler.exception.ResourceAlreadyExistsException;
import by.bsuir.intelligentscheduler.exception.ResourceNotFoundException;
import by.bsuir.intelligentscheduler.validator.TaskValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService extends AbstractService<Task> {

    private final TaskDao taskDao;
    private final TaskValidator taskValidator;

    @Autowired
    public TaskService(TaskDao taskDao, TaskValidator taskValidator) {
        this.taskDao = taskDao;
        this.taskValidator = taskValidator;
    }

    /**
     * Returns {@link Task} objects from a database without any filtering of a particular {@link User}.
     *
     * @param userId - {@link User} id
     * @param page  - the number of a page to show.
     * @param pageSize - the number of {@link Task} objects on a page.
     * @return a {@link List} of {@link Task} objects.
     */
    public List<Task> getAllByUserId(long userId, int page, int pageSize) {
        checkPaginationParameters(page, pageSize);
        List<Task> tasks = taskDao.getAllByUserId(userId);

        return getPaginated(tasks, pageSize, (page-1)*pageSize);
    }

    /**
     * Returns a {@link Task} object from a database by its id or throws {@link ResourceNotFoundException}
     * if nothing is retrieved from a database.
     *
     * @param id - the {@link Task} object's id that is to be retrieved from a database.
     * @return {@link Task} object.
     */
    public Task getById(long id) {
        Optional<Task> optionalOrder = taskDao.getById(id);
        return optionalOrder.orElseThrow(() -> new ResourceNotFoundException());
    }

    /**
     * Creates a {@link Task} object in a database or throws {@link RequiredFieldsMissingException} if some fields
     * required for creation are missing.
     *
     * @param task - the {@link Task} object that is to be created in a database.
     */
    public void create(Task task) {
        taskValidator.validateForCreation(task);
        taskDao.create(task);
    }

    /**
     * Deletes a {@link Task} object in a database by its id or throws {@link ResourceNotFoundException} if the object
     * with such id doesn't exist.
     *
     * @param id - the {@link Task} object's id that is to be deleted in a database.
     */
    public void delete(long id) {
        Optional<Task> optionalTask = taskDao.getById(id);
        Task task = optionalTask.orElseThrow(() -> new ResourceNotFoundException());

        taskDao.delete(task.getId());
    }
}
