package org.exceptions;

public class InvalidIdException extends Exception
{
    // Parameterless Constructor
    public InvalidIdException() {}

    // Constructor that accepts a message
    public InvalidIdException(String message)
    {
        super(message);
    }
}
