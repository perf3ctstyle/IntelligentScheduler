package by.bsuir.intelligentscheduler.config;

import by.bsuir.intelligentscheduler.dao.ProjectDao;
import by.bsuir.intelligentscheduler.dao.TaskDao;
import by.bsuir.intelligentscheduler.dao.UserDao;
import by.bsuir.intelligentscheduler.service.ProjectService;
import by.bsuir.intelligentscheduler.service.TaskService;
import by.bsuir.intelligentscheduler.service.UserService;
import by.bsuir.intelligentscheduler.validator.ProjectValidator;
import by.bsuir.intelligentscheduler.validator.TaskValidator;
import by.bsuir.intelligentscheduler.validator.UserValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan("by.bsuir.intelligentscheduler")
@EnableWebMvc
public class ApplicationConfig implements WebMvcConfigurer {

    @Bean
    public ProjectDao projectDao() {
        return new ProjectDao();
    }

    @Bean
    public TaskDao taskDao() {
        return new TaskDao();
    }

    @Bean
    public UserDao userDao() {
        return new UserDao();
    }

    @Bean
    public ProjectService projectService(ProjectDao projectDao, ProjectValidator projectValidator) {
        return new ProjectService(projectDao, projectValidator);
    }

    @Bean
    public TaskService taskService(TaskDao taskDao, TaskValidator taskValidator) {
        return new TaskService(taskDao, taskValidator);
    }

    @Bean
    public UserService userService(UserDao userDao, UserValidator userValidator, BCryptPasswordEncoder passwordEncoder) {
        return new UserService(userDao, userValidator, passwordEncoder);
    }

    @Bean
    public ProjectValidator projectValidator(TaskValidator taskValidator) {
        return new ProjectValidator(taskValidator);
    }

    @Bean
    public TaskValidator taskValidator() {
        return new TaskValidator();
    }

    @Bean
    public UserValidator userValidator() {
        return new UserValidator();
    }
}
