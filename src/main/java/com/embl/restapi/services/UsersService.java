package com.embl.restapi.services;

import com.embl.restapi.dto.Users;
import com.embl.restapi.exceptions.InvalidUserException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsersService
{
    void createUser(Users users);
    void resetPassword(Users users) throws InvalidUserException;
    void deleteUser(Users users);
    List<Users> getUsers();
}
