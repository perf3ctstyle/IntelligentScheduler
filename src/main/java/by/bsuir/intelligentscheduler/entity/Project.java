package by.bsuir.intelligentscheduler.entity;

import java.util.List;
import java.util.Objects;

public class Project {

    private Long id;
    private String name;
    private List<Task> tasks;
    private List<Project> projects;

    public Project(String name, List<Task> tasks, List<Project> projects) {
        this.name = name;
        this.tasks = tasks;
        this.projects = projects;
    }

    public Project(Long id, String name, List<Task> tasks, List<Project> projects) {
        this(name, tasks, projects);
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

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Project project = (Project) o;
        return name.equals(project.name) && tasks.equals(project.tasks) && projects.equals(project.projects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, tasks, projects);
    }
}
