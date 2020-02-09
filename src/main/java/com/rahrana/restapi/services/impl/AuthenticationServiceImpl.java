/*
 * 2020.
 * Author: Rahul Rana
 */

package com.rahrana.restapi.services.impl;

import com.rahrana.restapi.dto.AuthenticationRequest;
import com.rahrana.restapi.exceptions.InvalidCredentialsException;
import com.rahrana.restapi.services.AuthenticationService;
import com.rahrana.restapi.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service public class AuthenticationServiceImpl implements AuthenticationService
{
    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private MyUserDetailsService userDetailsService;
    @Autowired private JwtUtil jwtTokenUtil;

    @Override public String generateJwtToken(
            AuthenticationRequest authenticationRequest)
            throws InvalidCredentialsException
    {
        try
        {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()));
        }
        catch (BadCredentialsException e)
        {
            throw new InvalidCredentialsException(e);
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        return jwtTokenUtil.generateToken(userDetails);
    }
}
