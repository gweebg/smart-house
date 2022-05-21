package org.Exceptions;

public class NonExistentHouseException extends Exception
{
    public NonExistentHouseException() {}
    public NonExistentHouseException(String message)
    {
        super(message);
    }
}
