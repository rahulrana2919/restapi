/*
 * 2020.
 * Author: Rahul Rana
 */

package com.rahrana.restapi.services;

import com.rahrana.restapi.dto.AuthenticationRequest;
import com.rahrana.restapi.exceptions.InvalidCredentialsException;

public interface AuthenticationService
{
    String generateJwtToken(AuthenticationRequest authenticationRequest)
            throws InvalidCredentialsException;
}
