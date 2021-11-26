package by.bsuir.intelligentscheduler.controller;

import by.bsuir.intelligentscheduler.dto.AuthenticationRequestDto;
import by.bsuir.intelligentscheduler.dto.AuthenticationResponseDto;
import by.bsuir.intelligentscheduler.dto.ExceptionResponseDto;
import by.bsuir.intelligentscheduler.entity.User;
import by.bsuir.intelligentscheduler.exception.RequiredFieldsMissingException;
import by.bsuir.intelligentscheduler.exception.ResourceAlreadyExistsException;
import by.bsuir.intelligentscheduler.exception.ResourceNotFoundException;
import by.bsuir.intelligentscheduler.security.JwtTokenProvider;
import by.bsuir.intelligentscheduler.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    private static final String ID_PATH = "/{id}";
    private static final String LOGIN_PATH = "/login";

    @Autowired
    public UserController(UserService userService, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping(value = LOGIN_PATH)
    public ResponseEntity<?> login(@RequestBody AuthenticationRequestDto authRequestDto) {
        String login = authRequestDto.getLogin();
        String password = authRequestDto.getPassword();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));

        User user = userService.getByLogin(login);
        String token = jwtTokenProvider.createToken(user);

        AuthenticationResponseDto responseDto = new AuthenticationResponseDto(login, token);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * Returns all {@link User} objects from a database.
     *
     * @param page  - the number of a page to show.
     * @param pageSize - the number of {@link User} objects on a page.
     * @return {@link ResponseEntity} with a {@link HttpStatus} and a {@link List} of {@link User} objects.
     */
    @GetMapping()
    public ResponseEntity<?> getAll(@RequestParam int page, @RequestParam int pageSize) {
        List<User> users = userService.getAll(page, pageSize);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /**
     * Returns a {@link User} object from a database by its id or throws {@link ResourceNotFoundException}
     * if nothing is retrieved from a database.
     *
     * @param id - the {@link User} object's id that is to be retrieved from a database.
     * @return {@link ResponseEntity} with a {@link HttpStatus} and a {@link User} object
     * or a {@link ExceptionResponseDto} object.
     */
    @GetMapping(value = ID_PATH)
    public ResponseEntity<?> getById(@PathVariable long id) {
        User user = userService.getById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Creates a {@link User} object in a database or throws {@link RequiredFieldsMissingException} if some fields
     * required for creation are missing or {@link ResourceAlreadyExistsException} if the User with the same login already exists.
     *
     * @param user - the {@link User} object that is to be created in a database.
     * @return {@link ResponseEntity} with a {@link HttpStatus} alone
     * or additionally with a {@link ExceptionResponseDto} object.
     */
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody User user) {
        userService.create(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Deletes a {@link User} object in a database by its id or throws {@link ResourceNotFoundException} if the object
     * with such id doesn't exist.
     *
     * @param id - the {@link User} object's id that is to be deleted in a database.
     * @return {@link ResponseEntity} with a {@link HttpStatus} alone
     * or additionally with a {@link ExceptionResponseDto} object.
     */
    @DeleteMapping(value = ID_PATH)
    public ResponseEntity<?> deleteById(@PathVariable long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
