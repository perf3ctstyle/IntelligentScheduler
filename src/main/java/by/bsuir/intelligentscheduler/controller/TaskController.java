package by.bsuir.intelligentscheduler.controller;

import by.bsuir.intelligentscheduler.dto.ExceptionResponseDto;
import by.bsuir.intelligentscheduler.entity.Task;
import by.bsuir.intelligentscheduler.exception.ResourceAlreadyExistsException;
import by.bsuir.intelligentscheduler.exception.ResourceNotFoundException;
import by.bsuir.intelligentscheduler.exception.RequiredFieldsMissingException;
import by.bsuir.intelligentscheduler.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    private static final String ID = "id";
    private static final String ID_PATH = "/{id}";

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Returns {@link Task} objects from a database without any filtering.
     *
     * @param page  - the number of a page to show.
     * @param pageSize - the number of {@link Task} objects on a page.
     * @return {@link ResponseEntity} with a {@link HttpStatus} and a {@link List} of {@link Task} objects.
     */
    @GetMapping()
    public ResponseEntity<?> getAllByUserId(@RequestParam Long userId,
                                            @RequestParam int page,
                                            @RequestParam int pageSize) {
        List<Task> tasks = taskService.getAllByUserId(userId, page, pageSize);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    /**
     * Returns a {@link Task} object from a database by its id or throws {@link ResourceNotFoundException}
     * if nothing is retrieved from a database.
     *
     * @param id - the {@link Task} object's id that is to be retrieved from a database.
     * @return {@link ResponseEntity} with a {@link HttpStatus} and a {@link Task} object or a {@link ExceptionResponseDto} object.
     */
    @GetMapping(value = ID_PATH)
    public ResponseEntity<?> getById(@PathVariable(ID) long id) {
        Task task = taskService.getById(id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    /**
     * Creates a {@link Task} object in a database or throws {@link RequiredFieldsMissingException} if some fields
     * required for creation are missing.
     *
     * @param task - the {@link Task} object that is to be created in a database.
     * @return {@link ResponseEntity} with a {@link HttpStatus} alone or additionally with a {@link ExceptionResponseDto} object.
     */
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody Task task) {
        taskService.create(task);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Deletes a {@link Task} object in a database by its id or throws {@link ResourceNotFoundException} if the object
     * with such id doesn't exist.
     *
     * @param id - the {@link Task} object's id that is to be deleted in a database.
     * @return {@link ResponseEntity} with a {@link HttpStatus} alone or additionally with a {@link ExceptionResponseDto} object.
     */
    @DeleteMapping(value = ID_PATH)
    public ResponseEntity<?> deleteById(@PathVariable(ID) long id) {
        taskService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
