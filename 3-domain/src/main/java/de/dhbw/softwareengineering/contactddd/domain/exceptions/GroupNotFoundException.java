package de.dhbw.softwareengineering.contactddd.domain.exceptions;

public class GroupNotFoundException extends IllegalArgumentException{
    public GroupNotFoundException(String message){
        super(message);
    }
}
