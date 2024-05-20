package de.dhbw.softwareengineering.contactddd.domain.exceptions;

public class InvalidContactFieldException extends IllegalArgumentException {
    public InvalidContactFieldException(String message) {
        super(message);
    }
}
