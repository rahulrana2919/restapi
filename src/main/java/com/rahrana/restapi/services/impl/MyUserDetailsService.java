/*
 * 2020.
 * Author: Rahul Rana
 */

package com.rahrana.restapi.services.impl;

import com.rahrana.restapi.dto.Users;
import com.rahrana.restapi.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service public class MyUserDetailsService implements UserDetailsService
{
    @Autowired private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException
    {
        Optional<Users> user = userRepository.findById(username);
        if (user.isEmpty())
        {
            throw new UsernameNotFoundException("Username not found in DB");
        }
        return new User(user.get().getUsername(), user.get().getPassword(),
                new ArrayList<>());
    }
}
