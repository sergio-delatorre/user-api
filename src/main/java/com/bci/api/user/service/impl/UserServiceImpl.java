package com.bci.api.user.service.impl;

import com.bci.api.user.dto.AddedUserDto;
import com.bci.api.user.dto.UserDto;
import com.bci.api.user.repository.UserRepository;
import com.bci.api.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public AddedUserDto create(UserDto userDto) {
        return null;
    }
}
