package com.bci.api.user.service;

import com.bci.api.user.model.dto.AddedUserDto;
import com.bci.api.user.model.dto.UserDto;

import java.util.List;

public interface UserService {

    AddedUserDto create(UserDto userDto);

    List<UserDto> getAll();

    UserDto findById(String id);
}
