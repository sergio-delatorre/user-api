package com.bci.api.user.service;

import com.bci.api.user.model.dto.AddedUserDto;
import com.bci.api.user.model.dto.UserDto;

import java.util.List;

/**
 * Defines the methods associated with the User operations,
 * which must be implemented
 *
 * @author Sergio de la Torre
 * @see AddedUserDto
 * @see UserDto
 * @version 1.1
 * @since 2024-04-17
 */
public interface UserService {

    /**
     * Validates email existence, create token and encode password to save a user
     *  	@param userDto   User info with list of phones
     */
    AddedUserDto create(UserDto userDto);

    /**
     * Returns a list of all users
     */
    List<UserDto> getAll();

    /**
     * Returns a user by:
     *  	@param id   User Id
     */
    UserDto findById(String id);
}
