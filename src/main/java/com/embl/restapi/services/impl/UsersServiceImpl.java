package com.embl.restapi.services.impl;

import com.embl.restapi.dto.Users;
import com.embl.restapi.exceptions.InvalidUserException;
import com.embl.restapi.repo.UserRepository;
import com.embl.restapi.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
