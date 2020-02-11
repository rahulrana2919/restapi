/*
 * 2020.
 * Author: Rahul Rana
 */

package com.rahrana.restapi.controller;

import com.rahrana.restapi.dto.AuthenticationRequest;
import com.rahrana.restapi.dto.AuthenticationResponse;
import com.rahrana.restapi.exceptions.InvalidCredentialsException;
import com.rahrana.restapi.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
public class AuthenticationController
{
    @Autowired AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(
            @RequestBody AuthenticationRequest authenticationRequest)
            throws InvalidCredentialsException
    {
        final String jwt = authenticationService.generateJwtToken(
                authenticationRequest);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
