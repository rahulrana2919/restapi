/*
 * 2020.
 * Author: Rahul Rana
 */

package com.rahrana.restapi.exceptions;

public class InvalidCredentialsException extends Exception
{
    public InvalidCredentialsException(Exception e)
    {
        super("Either username or Password is incorrect " + e);
    }
}
