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
import org.jmantic.api.context.DefaultScContext;
import org.jmantic.scmemory.model.ScMemory;
import org.jmantic.scmemory.websocketmemory.sync.SyncOstisScMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.net.URI;
import java.net.URISyntaxException;

@Configuration
@ComponentScan("by.bsuir.intelligentscheduler")
@EnableWebMvc
public class ApplicationConfig implements WebMvcConfigurer {

    @Bean
    public ScMemory scMemory() throws URISyntaxException {
        return new SyncOstisScMemory(new URI("ws://localhost:8090/ws_json"));
    }

    @Bean
    public DefaultScContext defaultScContext(ScMemory scMemory) {
        return new DefaultScContext(scMemory);
    }

    @Bean
    public ProjectDao projectDao(ScMemory scMemory, DefaultScContext scContext) {
        return new ProjectDao(scMemory, scContext);
    }

    @Bean
    public TaskDao taskDao(ScMemory scMemory, DefaultScContext scContext) {
        return new TaskDao(scMemory, scContext);
    }

    @Bean
    public UserDao userDao(ScMemory scMemory, DefaultScContext scContext) {
        return new UserDao(scMemory, scContext);
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
