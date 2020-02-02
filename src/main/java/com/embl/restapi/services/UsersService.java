package com.embl.restapi.services;

import com.embl.restapi.dto.Users;
import com.embl.restapi.exceptions.InvalidUserException;
import org.springframework.stereotype.Service;

@Service
public interface UsersService
{
    void createUser(Users users);
    void resetPassword(Users users) throws InvalidUserException;
    void deleteUser(Users users);
}
