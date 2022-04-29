package org.exceptions;

public class NegativeDeviceIdException extends Exception
{
    // Parameterless Constructor
    public NegativeDeviceIdException() {}

    // Constructor that accepts a message
    public NegativeDeviceIdException(String message)
    {
        super(message);
    }
}
