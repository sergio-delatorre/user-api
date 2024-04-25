package com.bci.api.user.controller;

import com.bci.api.user.model.dto.AddedUserDto;
import com.bci.api.user.model.dto.UserDto;
import com.bci.api.user.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Receive requests related to users
 * <p>
 * Includes the following operations
 * <ul>
 * 	<li> Register a new user with list of phones
 * 	<li> Lists users
 * 	<li> Get a user by its ID
 * </ul>
 *
 * @author Sergio de la Torre
 * @see AddedUserDto
 * @see UserDto
 * @see UserService
 * @version 1.1
 * @since 2024-04-17
 */
@RestController
@RequestMapping("/users")
@Api(tags = "Users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Receive the request for creating a new User with its phones
     * @param userDto
     * 			user info with list of phones
     * @return	ResponseEntity with the user info, generated id and token
     * 			{@link ResponseEntity<AddedUserDto>}
     */
    @PostMapping("/sign-up")
    @ApiOperation(value = "Create a new user", notes = "Creates a new user with the provided phones")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User created successfully"),
            @ApiResponse(code = 400, message = "Invalid input"),
            @ApiResponse(code = 422, message = "The request could not be processed")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userDto", value = "User object that needs to be created", required = true
                    , dataType = "UserDto", paramType = "body"
                    , example = "{\n  \"email\": \"sergio20897@gmail.com\",\n  \"name\": \"Sergio de la Torre\",\n  \"password\": \"qCYgPz#@cwG7\"" +
                    ",\n  \"phones\": [\n    {\n      \"citycode\": \"01\",\n      \"countrycode\": \"51\",\n      \"number\": \"999123654\"\n    }\n  ]\n}")
    })
    public ResponseEntity<AddedUserDto> create(@RequestBody @Valid UserDto userDto){
        AddedUserDto addedUserDto = userService.create(userDto);
        return new ResponseEntity<>(addedUserDto, HttpStatus.CREATED);
    }

    /**
     * Receive the request for listing all users
     * @return	lists of users
     * 			{@link List<UserDto>}
     */
    @GetMapping
    @ApiOperation(value = "Get all users", notes = "Returns a list of all users")
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    /**
     * Receive the request for the search of user by id
     * @param id
     * 			user id
     * @return	ResponseEntity with the user info
     * 			{@link ResponseEntity<UserDto>}
     */
    @GetMapping("{id}")
    @ApiOperation(value = "Get by Id", notes = "Returns a User by its identifier")
    public ResponseEntity<UserDto> getById(@PathVariable("id") String id) {
        UserDto userDto = userService.findById(id);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

}
