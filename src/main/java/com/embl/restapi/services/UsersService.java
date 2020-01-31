package com.embl.restapi.services;

import com.embl.restapi.dto.Users;
import org.springframework.stereotype.Service;

@Service
public interface UsersService
{
    public void createUser(Users users);
}
