package org.exceptions;

public class NonexistentDeviceException extends Exception
{
    public NonexistentDeviceException() {}
    public NonexistentDeviceException(String message)
    {
        super(message);
    }
}
