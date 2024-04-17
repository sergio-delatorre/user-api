package com.bci.api.user.controller;

import com.bci.api.user.dto.AddedUserDto;
import com.bci.api.user.dto.UserDto;
import com.bci.api.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
@Api(tags = "Endpoints for managing users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    @ApiOperation(value = "Create a new user", notes = "Creates a new user with the provided data")
    public ResponseEntity<AddedUserDto> create(@RequestBody @Valid UserDto userDto){
        AddedUserDto addedUserDto = userService.create(userDto);
        return new ResponseEntity<>(addedUserDto, HttpStatus.CREATED);
    }

    @GetMapping
    @ApiOperation(value = "Get all users", notes = "Returns a list of all users")
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Token válido");
    }
}
