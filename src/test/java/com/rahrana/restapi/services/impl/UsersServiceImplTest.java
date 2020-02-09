/*
 * 2020.
 * Author: Rahul Rana
 */

package com.rahrana.restapi.services.impl;

import com.rahrana.restapi.dto.Users;
import com.rahrana.restapi.exceptions.InvalidUserException;
import com.rahrana.restapi.repo.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class UsersServiceImplTest
{
    public static final Users INVALID_USER = new Users("abc", "def", false);
    @InjectMocks UsersServiceImpl usersService;
    @Mock UserRepository userRepository;
    Users users;

    @BeforeEach void setUp()
    {
        initMocks(this);
        users = new Users("username", "password", true);
        when(userRepository.findById(users.getUsername()))
                .thenReturn(Optional.of(users));
    }

    @Test void createUser()
    {
        usersService.createUser(users);
        verify(userRepository, atMostOnce()).save(any(Users.class));
    }

    @Test void updateUser() throws InvalidUserException
    {
        usersService.resetPassword(users);
        verify(userRepository, atMostOnce()).save(any(Users.class));
    }

    @Test void updateUser_Exception()
    {
        try
        {
            usersService.resetPassword(INVALID_USER);
            fail("This should throw exception");
        }
        catch (Exception e)
        {
            assertTrue(e instanceof InvalidUserException);
        }
    }

    @Test void deleteUser()
    {
        usersService.deleteUser(users);
        verify(userRepository, atMostOnce()).delete(any(Users.class));
    }
}