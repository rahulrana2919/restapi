package com.embl.restapi.exceptions;

public class InvalidCredentialsException extends RuntimeException
{
    public InvalidCredentialsException()
    {
        super("Either username or Password is incorrect");
    }
}
