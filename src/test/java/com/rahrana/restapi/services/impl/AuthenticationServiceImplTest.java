/*
 * 2020.
 * Author: Rahul Rana
 */

package com.rahrana.restapi.services.impl;

import com.rahrana.restapi.dto.AuthenticationRequest;
import com.rahrana.restapi.exceptions.InvalidCredentialsException;
import com.rahrana.restapi.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class AuthenticationServiceImplTest
{
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    @InjectMocks private AuthenticationServiceImpl authenticationService;
    private AuthenticationRequest authenticationRequest;

    @Mock private AuthenticationManager authenticationManager;
    @Mock private MyUserDetailsService userDetailsService;
    @Mock private JwtUtil jwtTokenUtil;
    private static String JWT = "jwt";

    @BeforeEach void setUp()
    {
        initMocks(this);
        authenticationRequest = new AuthenticationRequest(USERNAME, PASSWORD);
        UserDetails userDetails = new User(USERNAME, PASSWORD, List.of());
        when(authenticationManager.authenticate(any())).thenReturn(any());
        when(userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername()))
                .thenReturn(userDetails);
        when(jwtTokenUtil.generateToken(userDetails)).thenReturn(JWT);
    }

    @Test void createAuthenticationToken() throws InvalidCredentialsException
    {
        String authenticationToken = authenticationService
                .generateJwtToken(authenticationRequest);
        assertEquals(JWT, authenticationToken);
    }

    @Test void createAuthenticationToken_Exception()
    {
        when(authenticationManager.authenticate(any()))
                .thenThrow(BadCredentialsException.class);
        try
        {
            authenticationService
                    .generateJwtToken(authenticationRequest);
            fail("Should throw exception");
        }
        catch (Exception e)
        {
            assertTrue(e instanceof InvalidCredentialsException);
        }
    }
}