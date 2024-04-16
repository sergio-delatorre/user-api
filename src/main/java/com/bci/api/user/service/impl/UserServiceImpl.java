package com.bci.api.user.service.impl;

import com.bci.api.user.dto.AddedUserDto;
import com.bci.api.user.dto.PhoneDto;
import com.bci.api.user.dto.UserDto;
import com.bci.api.user.model.Phone;
import com.bci.api.user.model.User;
import com.bci.api.user.repository.UserRepository;
import com.bci.api.user.service.UserService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.mapper = modelMapper;
    }

    @Override
    @Transactional
    public AddedUserDto create(UserDto userDto) {
        User newUser = mapper.map(userDto, User.class);
        newUser.setToken(UUID.fromString(userDto.getToken()));
        for (Phone phone : newUser.getPhones()) {
            phone.setUser(newUser);
        }
        newUser = userRepository.save(newUser);
        return mapper.map(newUser, AddedUserDto.class);
    }

    @Override
    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> mapper.map(user,UserDto.class))
                .collect(Collectors.toList());
    }
}
