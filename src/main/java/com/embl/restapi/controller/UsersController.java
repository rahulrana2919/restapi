package com.embl.restapi.controller;

import com.embl.restapi.dto.Users;
import com.embl.restapi.exceptions.InvalidUserException;
import com.embl.restapi.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController
{
    @Autowired private UsersService usersService;

    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Users users)
    {
        usersService.createUser(users);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Users> resetPassword(@RequestBody Users users)
            throws InvalidUserException
    {
        usersService.resetPassword(users);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @DeleteMapping
    public ResponseEntity<Users> deleteUser(@RequestBody Users users)
    {
        usersService.deleteUser(users);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
