package by.bsuir.intelligentscheduler.validator;

import by.bsuir.intelligentscheduler.entity.Task;
import by.bsuir.intelligentscheduler.exception.RequiredFieldsMissingException;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

public class TaskValidator {

    private static final int MIN_TASK_PRIORITY = 1;
    private static final int MAX_TASK_PRIORITY = 4;

    public void validateForCreation(Task task) {
        validateName(task.getName());
        validatePriority(task.getPriority());
        validateDeadline(task.getDeadline());
        validateCompletionDateTime(task.getCompletionDateTime());
        validateTags(task.getTags());
    }

    private void validateName(String name) {
        if (StringUtils.isBlank(name)) {
            throw new RequiredFieldsMissingException();
        }
    }

    private void validatePriority(Integer priority) {
        if (priority == null) {
            throw new RequiredFieldsMissingException();
        }
        if (priority < MIN_TASK_PRIORITY || priority > MAX_TASK_PRIORITY) {
            throw new IllegalArgumentException();
        }
    }

    private void validateDeadline(LocalDateTime deadline) {
        if (deadline.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException();
        }
    }

    private void validateCompletionDateTime(LocalDateTime completionDateTime) {
        if (completionDateTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException();
        }
    }

    private void validateTags(List<String> tags) {
        for (String tag : tags) {
            if (StringUtils.isBlank(tag)) {
                throw new IllegalArgumentException();
            }
        }
    }
}
