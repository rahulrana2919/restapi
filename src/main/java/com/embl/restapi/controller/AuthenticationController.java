/*
 * 2020.
 * Author: Rahul Rana
 */

package com.embl.restapi.controller;

import com.embl.restapi.dto.AuthenticationRequest;
import com.embl.restapi.dto.AuthenticationResponse;
import com.embl.restapi.exceptions.InvalidCredentialsException;
import com.embl.restapi.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController
{
    @Autowired AuthenticationService authenticationService;

    @PostMapping(path = "/authenticate")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(
            @RequestBody AuthenticationRequest authenticationRequest)
            throws InvalidCredentialsException
    {
        final String jwt = authenticationService.generateJwtToken(
                authenticationRequest);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
