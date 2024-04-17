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

@RestController
@RequestMapping("/users")
@Api(tags = "Users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

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

    @GetMapping
    @ApiOperation(value = "Get all users", notes = "Returns a list of all users")
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Get by Id", notes = "Returns a User by its identifier")
    public ResponseEntity<UserDto> getById(@PathVariable("id") String userId) {
        UserDto userDto = userService.findById(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

}
