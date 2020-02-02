package com.embl.restapi.services.impl;

import com.embl.restapi.dto.Users;
import com.embl.restapi.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service public class MyUserDetailsService implements UserDetailsService
{
    private static Users defaultUser = new Users("user", "pass", true);

    @Autowired private UserRepository userRepository;

    @Override public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException
    {
        Users user = userRepository.findById(username).orElse(defaultUser);
        return new User(user.getUsername(), user.getPassword(),
                new ArrayList<>());
    }
}
