package com.embl.restapi.services.impl;

import com.embl.restapi.dto.Users;
import com.embl.restapi.repo.UserRepository;
import com.embl.restapi.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService
{
    @Autowired
    private UserRepository userRepository;

    @Override public void createUser(Users users)
    {
        userRepository.save(users);
    }
}
