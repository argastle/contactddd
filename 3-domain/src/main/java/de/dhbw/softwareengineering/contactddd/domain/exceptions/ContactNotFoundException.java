package de.dhbw.softwareengineering.contactddd.domain.exceptions;

public class ContactNotFoundException extends IllegalArgumentException{
    public ContactNotFoundException(String message) {
        super(message);
    }
}
