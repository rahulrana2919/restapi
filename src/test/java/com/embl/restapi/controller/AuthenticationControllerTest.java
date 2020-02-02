/*
 * 2020.
 * Author: Rahul Rana
 */

package com.embl.restapi.controller;

import com.embl.restapi.dto.AuthenticationRequest;
import com.embl.restapi.dto.AuthenticationResponse;
import com.embl.restapi.exceptions.InvalidCredentialsException;
import com.embl.restapi.services.impl.MyUserDetailsService;
import com.embl.restapi.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class AuthenticationControllerTest
{
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    @InjectMocks private AuthenticationController authenticationController;
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
        ResponseEntity<AuthenticationResponse> authenticationToken = authenticationController
                .createAuthenticationToken(authenticationRequest);
        assertEquals(HttpStatus.OK, authenticationToken.getStatusCode());
        assertEquals(new AuthenticationResponse(JWT),
                authenticationToken.getBody());
    }

    @Test void createAuthenticationToken_Exception()
    {
        when(authenticationManager.authenticate(any()))
                .thenThrow(BadCredentialsException.class);
        try
        {
            authenticationController
                    .createAuthenticationToken(authenticationRequest);
            fail("Should throw exception");
        }
        catch (Exception e)
        {
            assertTrue(e instanceof InvalidCredentialsException);
        }
    }
}