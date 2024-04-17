package com.bci.api.user.service.impl;

import com.bci.api.user.dto.AddedUserDto;
import com.bci.api.user.dto.UserDto;
import com.bci.api.user.exception.ResourceNotFoundException;
import com.bci.api.user.exception.UnprocessableEntityException;
import com.bci.api.user.model.Phone;
import com.bci.api.user.model.User;
import com.bci.api.user.repository.UserRepository;
import com.bci.api.user.security.JwtTokenProvider;
import com.bci.api.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public AddedUserDto create(UserDto userDto) {
        User newUser = mapper.map(userDto, User.class);
        if(userRepository.findByEmail(newUser.getEmail()).isPresent()){
            throw new UnprocessableEntityException("El correo ya está registrado.");
        }
        newUser.setToken(jwtTokenProvider.generateToken());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        for (Phone phone : newUser.getPhones()) {
            phone.setUser(newUser);
        }
        newUser = userRepository.save(newUser);
        return mapper.map(newUser, AddedUserDto.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> mapper.map(user,UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto findById(String id) {
        UUID uuid = UUID.fromString(id);
        User user = userRepository.findById(uuid).orElseThrow(
                () -> new ResourceNotFoundException("User not found"));
        return mapper.map(user, UserDto.class);
    }
}
