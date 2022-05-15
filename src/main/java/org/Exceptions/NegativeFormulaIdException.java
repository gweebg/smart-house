package org.Exceptions;

public class NegativeFormulaIdException extends Exception{

    // Parameterless Constructor
    public NegativeFormulaIdException() {}

    // Constructor that accepts a message
    public NegativeFormulaIdException(String message)
    {
        super(message);
    }
}
