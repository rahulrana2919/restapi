/*
 * 2020.
 * Author: Rahul Rana
 */

package com.rahrana.restapi.exceptions;

public class InvalidUserException extends Exception
{
    public InvalidUserException()
    {
        super("Username does not exist.");
    }
}
