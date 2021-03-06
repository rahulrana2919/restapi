/*
 * 2020.
 * Author: Rahul Rana
 */

package com.rahrana.restapi.services.impl;

import com.rahrana.restapi.dto.Users;
import com.rahrana.restapi.exceptions.InvalidUserException;
import com.rahrana.restapi.repo.UserRepository;
import com.rahrana.restapi.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service public class UsersServiceImpl implements UsersService
{
    @Autowired private UserRepository userRepository;

    @Override public void createUser(Users users)
    {
        userRepository.save(users);
    }

    @Override public void resetPassword(Users users) throws InvalidUserException
    {
        Optional<Users> user = userRepository.findById(users.getUsername());
        user.ifPresent(dbUser -> userRepository.save(users));
        user.orElseThrow(InvalidUserException::new);
    }

    @Override public void deleteUser(Users users)
    {
        userRepository.delete(users);
    }

    @Override
    public List<Users> getUsers()
    {
        return userRepository.findAll();
    }
}
