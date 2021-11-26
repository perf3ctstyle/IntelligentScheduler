package by.bsuir.intelligentscheduler.controller;

import by.bsuir.intelligentscheduler.dto.ExceptionResponseDto;
import by.bsuir.intelligentscheduler.entity.Project;
import by.bsuir.intelligentscheduler.entity.User;
import by.bsuir.intelligentscheduler.exception.ResourceAlreadyExistsException;
import by.bsuir.intelligentscheduler.exception.ResourceNotFoundException;
import by.bsuir.intelligentscheduler.exception.RequiredFieldsMissingException;
import by.bsuir.intelligentscheduler.service.ProjectService;
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
@RequestMapping(value = "/project")
public class ProjectController {

    private final ProjectService projectService;

    private static final String ID = "id";
    private static final String ID_PATH = "/{id}";

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    /**
     * Returns a {@link List} of {@link Project} objects from a database.
     *
     * @param userId - the {@link User} id whose {@link Project} objects will be searched for.
     * @param page  - the number of a page to show.
     * @param pageSize - the number of {@link Project} objects on a page.
     * @return {@link ResponseEntity} with a {@link HttpStatus} and a {@link List} of {@link User} objects.
     */
    @GetMapping()
    public ResponseEntity<?> getAllByUserId(@RequestParam Long userId,
                                            @RequestParam int page,
                                            @RequestParam int pageSize) {
        List<Project> projects = projectService.getAllByUserId(userId, page, pageSize);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    /**
     * Returns a {@link Project} object from a database by its id or throws {@link ResourceNotFoundException}
     * if nothing is retrieved from a database.
     *
     * @param id - the {@link Project} object's id that is to be retrieved from a database.
     * @return {@link ResponseEntity} with a {@link HttpStatus} and a {@link Project} object or a {@link ExceptionResponseDto} object.
     */
    @GetMapping(value = ID_PATH)
    public ResponseEntity<?> getById(@PathVariable(ID) long id) {
        Project project = projectService.getById(id);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    /**
     * Creates a {@link Project} object in a database or throws {@link RequiredFieldsMissingException} if some fields
     * required for creation are missing.
     *
     * @param project - the {@link Project} object that is to be created in a database.
     * @return {@link ResponseEntity} with a {@link HttpStatus} alone or additionally with a {@link ExceptionResponseDto} object.
     */
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody Project project) {
        projectService.create(project);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Deletes a {@link Project} object in a database by its id or throws {@link ResourceNotFoundException} if the object
     * with such id doesn't exist.
     *
     * @param id - the {@link Project} object's id that is to be deleted in a database.
     * @return {@link ResponseEntity} with a {@link HttpStatus} alone or additionally with a {@link ExceptionResponseDto} object.
     */
    @DeleteMapping(value = ID_PATH)
    public ResponseEntity<?> deleteById(@PathVariable(ID) long id) {
        projectService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
