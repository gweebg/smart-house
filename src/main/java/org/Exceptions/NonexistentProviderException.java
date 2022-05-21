package org.Exceptions;

public class NonexistentProviderException extends Exception
{
    public NonexistentProviderException() {}
    public NonexistentProviderException(String message)
    {
        super(message);
    }
}
