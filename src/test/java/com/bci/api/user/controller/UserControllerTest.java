package com.bci.api.user.controller;

import com.bci.api.user.config.ApplicationConfig;
import com.bci.api.user.exception.ResourceNotFoundException;
import com.bci.api.user.exception.UnprocessableEntityException;
import com.bci.api.user.model.dto.AddedUserDto;
import com.bci.api.user.model.dto.PhoneDto;
import com.bci.api.user.model.dto.UserDto;
import com.bci.api.user.security.JwtTokenProvider;
import com.bci.api.user.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Import(ApplicationConfig.class)
public class UserControllerTest     {

    private static final String USER_PATH = "/users";

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @Test
    public void testCreateUserSuccess() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setEmail("sergio20897@gmail.com");
        userDto.setName("Sergio de la Torre");
        userDto.setPassword("qCYgPz#@cwG7");
        List<PhoneDto> phones = new ArrayList<>();
        PhoneDto phoneDto = new PhoneDto();
        phoneDto.setCitycode("01");
        phoneDto.setCountrycode("51");
        phoneDto.setNumber("999123654");
        phones.add(phoneDto);
        userDto.setPhones(phones);

        AddedUserDto addedUserDto = new AddedUserDto();
        String id = "40eb04d5-cd90-4c50-a72e-fe53e678c42e";
        addedUserDto.setId(id);
        addedUserDto.setEmail(userDto.getEmail());

        Mockito.when(userService.create(Mockito.any(UserDto.class))).thenReturn(addedUserDto);

        mockMvc.perform(post(USER_PATH+"/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", Is.is(id)))
                .andExpect(jsonPath("$.email", Is.is(userDto.getEmail())));

        Mockito.verify(userService, Mockito.times(1)).create(Mockito.any(UserDto.class));
    }

    @Test
    public void testCreateUserInvalidInput() throws Exception {
        UserDto invalidUserDto = new UserDto();

        mockMvc.perform(post(USER_PATH+"/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidUserDto)))
                .andExpect(status().isBadRequest());

        Mockito.verify(userService, Mockito.never()).create(Mockito.any(UserDto.class));
    }

    @Test
    public void testCreateUserUnprocessableEntity() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setEmail("example@mail.com");
        userDto.setPassword("gD64hgfDfg");
        userDto.setName("Sample");

        Mockito.when(userService.create(Mockito.any(UserDto.class))).thenThrow(new UnprocessableEntityException("Unable to process request"));

        mockMvc.perform(post(USER_PATH+"/sign-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isUnprocessableEntity());

        Mockito.verify(userService, Mockito.times(1)).create(Mockito.any(UserDto.class));
    }

        @Test
        public void testGetAllUsers() throws Exception {
            List<UserDto> users = new ArrayList<>();
            UserDto user1 = new UserDto();
            user1.setEmail("user1@example.com");
            users.add(user1);

            UserDto user2 = new UserDto();
            user2.setEmail("user2@example.com");
            users.add(user2);

            Mockito.when(userService.getAll()).thenReturn(users);

            mockMvc.perform(MockMvcRequestBuilders.get(USER_PATH)
                            .header("Authorization", "Bearer " + jwtTokenProvider.generateToken()))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", Matchers.hasSize(2)))
                    .andExpect(jsonPath("$[0].email", Is.is("user1@example.com")))
                    .andExpect(jsonPath("$[1].email", Is.is("user2@example.com")));

            Mockito.verify(userService, Mockito.times(1)).getAll();
        }

    @Test
    public void testGetUserByIdNotFound() throws Exception {
        String userId = "999999";

        Mockito.when(userService.findById(userId)).thenThrow(new ResourceNotFoundException("User not found"));

        mockMvc.perform(MockMvcRequestBuilders.get(USER_PATH+"/{id}", userId)
                    .header("Authorization", "Bearer " + jwtTokenProvider.generateToken()))
                .andExpect(status().isNotFound());

        Mockito.verify(userService, Mockito.times(1)).findById(userId);
    }
}
