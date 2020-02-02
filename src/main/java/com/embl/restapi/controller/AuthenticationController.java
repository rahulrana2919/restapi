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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController
{
    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private MyUserDetailsService userDetailsService;
    @Autowired private JwtUtil jwtTokenUtil;

    @PostMapping(path = "/authenticate")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(
            @RequestBody AuthenticationRequest authenticationRequest)
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

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
