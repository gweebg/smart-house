package org.Exceptions;

public class ExistingIdException extends Exception
{

    // Parameterless Constructor
    public ExistingIdException() {}

    // Constructor that accepts a message
    public ExistingIdException(String message)
    {
        super(message);
    }
}
