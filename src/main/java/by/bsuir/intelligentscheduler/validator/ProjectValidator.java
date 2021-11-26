package by.bsuir.intelligentscheduler.validator;

import by.bsuir.intelligentscheduler.entity.Project;
import by.bsuir.intelligentscheduler.entity.Task;
import by.bsuir.intelligentscheduler.exception.RequiredFieldsMissingException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProjectValidator {

    private final TaskValidator taskValidator;

    @Autowired
    public ProjectValidator(TaskValidator taskValidator) {
        this.taskValidator = taskValidator;
    }

    public void validateForCreation(Project project) {
        validateName(project.getName());
        validateTasks(project.getTasks());
        validateProjects(project.getProjects());
    }

    private void validateName(String name) {
        if (StringUtils.isBlank(name)) {
            throw new RequiredFieldsMissingException();
        }
    }

    private void validateTasks(List<Task> tasks) {
        for (Task task : tasks) {
            taskValidator.validateForCreation(task);
        }
    }

    private void validateProjects(List<Project> projects) {
        for (Project project : projects) {
            validateForCreation(project);
        }
    }
}
