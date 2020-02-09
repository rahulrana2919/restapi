/*
 * 2020.
 * Author: Rahul Rana
 */

package com.rahrana.restapi.services;

import com.rahrana.restapi.dto.Users;
import com.rahrana.restapi.exceptions.InvalidUserException;
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
