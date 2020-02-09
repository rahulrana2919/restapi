/*
 * 2020.
 * Author: Rahul Rana
 */

package com.rahrana.restapi.controller;

import com.rahrana.restapi.dto.Users;
import com.rahrana.restapi.exceptions.InvalidUserException;
import com.rahrana.restapi.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<Users>> getUsers()
    {
        return new ResponseEntity<>(usersService.getUsers(), HttpStatus.OK);
    }
}
