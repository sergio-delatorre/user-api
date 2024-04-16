package com.bci.api.user.service;

import com.bci.api.user.dto.AddedUserDto;
import com.bci.api.user.dto.UserDto;

public interface UserService {

    AddedUserDto create(UserDto userDto);
}
