package com.bci.api.user.controller;

import com.bci.api.user.dto.AddedUserDto;
import com.bci.api.user.dto.UserDto;
import com.bci.api.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@Tag(name = "User", description = "Endpoints for managing users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @Operation(summary = "Create a new user", description = "Creates a new user with the provided data")
    public ResponseEntity<AddedUserDto> create(@RequestBody @Valid UserDto userDto){
        AddedUserDto addedUserDto = userService.create(userDto);
        return new ResponseEntity<>(addedUserDto, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all users", description = "Returns a list of all users")
    public List<UserDto> getAll() {
        return userService.getAll();
    }
}
