/*
 * 2020.
 * Author: Rahul Rana
 */

package com.embl.restapi.services;

import com.embl.restapi.dto.AuthenticationRequest;
import com.embl.restapi.exceptions.InvalidCredentialsException;

public interface AuthenticationService
{
    String generateJwtToken(AuthenticationRequest authenticationRequest)
            throws InvalidCredentialsException;
}
