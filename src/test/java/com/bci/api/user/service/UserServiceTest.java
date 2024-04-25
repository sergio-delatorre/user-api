package com.bci.api.user.service;

import com.bci.api.user.exception.ResourceNotFoundException;
import com.bci.api.user.exception.UnprocessableEntityException;
import com.bci.api.user.model.dto.AddedUserDto;
import com.bci.api.user.model.dto.UserDto;
import com.bci.api.user.model.entity.User;
import com.bci.api.user.repository.UserRepository;
import com.bci.api.user.security.JwtTokenProvider;
import com.bci.api.user.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ModelMapper mapper;
    @Mock
    private JwtTokenProvider jwtTokenProvider;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void testCreateUser() {
        UserDto userDto = new UserDto();
        String email = "test@example.com";
        userDto.setEmail(email);
        userDto.setPassword("password");
        userDto.setPhones(Collections.emptyList());

        User newUser = new User();
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(userDto.getPassword());
        newUser.setToken("testToken");
        newUser.setPhones(Collections.emptyList());

        AddedUserDto addedUserDto = new AddedUserDto();
        addedUserDto.setEmail(newUser.getEmail());
        addedUserDto.setToken(newUser.getToken());

        Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.empty());
        Mockito.when(jwtTokenProvider.generateToken()).thenReturn("testToken");
        Mockito.when(mapper.map(userDto, User.class)).thenReturn(newUser);
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(newUser);
        Mockito.when(mapper.map(newUser, AddedUserDto.class)).thenReturn(addedUserDto);
        Mockito.when(passwordEncoder.encode(Mockito.any(CharSequence.class))).thenReturn("encodedPassword");

        AddedUserDto result = userService.create(userDto);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(newUser.getEmail(), result.getEmail());
        Assertions.assertEquals(newUser.getToken(), result.getToken());
    }

    @Test
    void testCreateUserWithEmailAlreadyExists() {
        UserDto userDto = new UserDto();
        String email = "existing@example.com";
        userDto.setEmail(email);
        userDto.setPassword("password");
        userDto.setPhones(Collections.emptyList());

        User user = new User();
        user.setEmail(email);

        Mockito.when(mapper.map(userDto, User.class)).thenReturn(user);
        Mockito.when(userRepository.findByEmail(email)).thenReturn(Optional.of(new User()));

        Assertions.assertThrows(UnprocessableEntityException.class, () -> userService.create(userDto));
    }


    @Test
    public void testFindUserById() {
        String id = "40eb04d5-cd90-4c50-a72e-fe53e678c42e";
        UUID uuid = UUID.fromString(id);
        User user = new User();
        UserDto userDto = new UserDto();
        Mockito.when(userRepository.findById(uuid)).thenReturn(Optional.of(user));
        Mockito.when(mapper.map(user, UserDto.class)).thenReturn(userDto);

        UserDto foundUserDto = userService.findById(id);

        Assertions.assertEquals(userDto, foundUserDto);
    }

    @Test
    public void testFindUserByIdNotFound() {
        String id = "1a7b3906-395f-4cf6-950e-394414f64704";
        UUID uuid = UUID.fromString(id);
        Mockito.when(userRepository.findById(uuid)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> userService.findById(id));
    }
}
