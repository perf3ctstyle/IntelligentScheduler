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

    public List<Task> getAllByUserId(long userId, int page, int pageSize) {
        checkPaginationParameters(page, pageSize);
        List<Task> tasks = taskDao.getAllByUserId(userId);

        return getPaginated(tasks, pageSize, (page-1)*pageSize);
    }

    public Task getById(long id) {
        Optional<Task> optionalTask = taskDao.getById(id);
        return optionalTask.orElseThrow(() -> new ResourceNotFoundException());
    }

    public void create(Task task) {
        taskValidator.validateForCreation(task);
        taskDao.create(task);
    }

    public void delete(long id) {
        Optional<Task> optionalTask = taskDao.getById(id);
        Task task = optionalTask.orElseThrow(() -> new ResourceNotFoundException());

        taskDao.delete(task.getId());
    }
}
