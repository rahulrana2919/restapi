/*
 * 2020.
 * Author: Rahul Rana
 */

package com.embl.restapi.services.impl;

import com.embl.restapi.dto.Users;
import com.embl.restapi.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable(value = "usersCache", condition = "#username!=null")
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
