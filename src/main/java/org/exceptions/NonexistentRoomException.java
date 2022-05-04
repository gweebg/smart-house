package org.exceptions;

public class NonexistentRoomException extends Exception
{
    public NonexistentRoomException() {}
    public NonexistentRoomException(String message)
    {
        super(message);
    }
}
