/*
 * 2020.
 * Author: Rahul Rana
 */

package com.embl.restapi.exceptions;

public class InvalidUserException extends Exception
{
    public InvalidUserException()
    {
        super("Username does not exist.");
    }
}
