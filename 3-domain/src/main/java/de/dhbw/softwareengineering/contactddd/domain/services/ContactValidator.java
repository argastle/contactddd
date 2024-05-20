package de.dhbw.softwareengineering.contactddd.domain.services;

import de.dhbw.softwareengineering.contactddd.domain.exceptions.InvalidContactFieldException;

import java.util.regex.Pattern;

public class ContactValidator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+?[0-9. ()-]{7,25}$");

    public static String validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new InvalidContactFieldException("Name cannot be null or empty.");
        }
        return name;
    }

    public static String validateEmail(String email) {
        if (email == null || email.isEmpty() || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new InvalidContactFieldException("Invalid email format.");
        }
        return email;
    }

    public static String validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty() || !PHONE_PATTERN.matcher(phoneNumber).matches()) {
            throw new InvalidContactFieldException("Invalid phone number format.");
        }
        return phoneNumber;
    }
}
