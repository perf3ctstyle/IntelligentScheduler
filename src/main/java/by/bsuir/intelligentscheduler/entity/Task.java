package by.bsuir.intelligentscheduler.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Task {

    private Long id;
    private String name;
    private Integer priority;
    private LocalDateTime deadline;
    private LocalDateTime completionDateTime;
    private List<String> tags;

    public Task(String name, Integer priority, LocalDateTime deadline, LocalDateTime completionDateTime, List<String> tags) {
        this.name = name;
        this.priority = priority;
        this.deadline = deadline;
        this.completionDateTime = completionDateTime;
        this.tags = tags;
    }

    public Task(Long id,
                String name,
                Integer priority,
                LocalDateTime deadline,
                LocalDateTime completionDateTime,
                List<String> tags) {
        this(name, priority, deadline, completionDateTime, tags);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public LocalDateTime getCompletionDateTime() {
        return completionDateTime;
    }

    public void setCompletionDateTime(LocalDateTime completionDateTime) {
        this.completionDateTime = completionDateTime;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        return name.equals(task.name) && priority.equals(task.priority) && deadline.equals(task.deadline) && completionDateTime.equals(task.completionDateTime) && tags.equals(task.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, priority, deadline, completionDateTime, tags);
    }
}
