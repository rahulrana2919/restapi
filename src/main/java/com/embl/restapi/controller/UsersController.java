package com.embl.restapi.controller;

import com.embl.restapi.dto.Users;
import com.embl.restapi.services.UsersService;
import com.embl.restapi.services.impl.MyUserDetailsService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController
{
    @Autowired private UsersService usersService;

    @PostMapping(path = "/users")
    public ResponseEntity<Users> createUser(@RequestBody Users users)
    {
        usersService.createUser(users);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
