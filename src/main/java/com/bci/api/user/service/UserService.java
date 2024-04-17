package com.bci.api.user.service;

import com.bci.api.user.dto.AddedUserDto;
import com.bci.api.user.dto.UserDto;

import java.util.List;

public interface UserService {

    AddedUserDto create(UserDto userDto);

    List<UserDto> getAll();

    UserDto findById(String id);
}
