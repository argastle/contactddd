package de.dhbw.softwareengineering.contactddd.domain.entities;

import de.dhbw.softwareengineering.contactddd.domain.values.ContactId;
import de.dhbw.softwareengineering.contactddd.domain.values.SocialMediaAccount;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

@Document(collection = "contacts")
public class Contact {
    @Id
    private ContactId id;

    private String name;

    private String email;

    private String phoneNumber;

    private Set<SocialMediaAccount> socialMediaAccounts;

    public Contact(ContactId id, String name, String email, String phoneNumber, Set<SocialMediaAccount> socialMediaAccounts) {
        this.id = id;
        validateName(name);
        this.name = name;
        this.email = validateEmail(email);
        this.phoneNumber = validatePhoneNumber(phoneNumber);
        this.socialMediaAccounts = socialMediaAccounts != null ? new HashSet<>(socialMediaAccounts) : new HashSet<>();
    }

    public Contact(String name, String email, String phoneNumber, Set<SocialMediaAccount> socialMediaAccounts) {
        this(new ContactId(), name, email, phoneNumber, socialMediaAccounts);
    }

    public Contact() {
    }

    public void updateContact(String name, String email, String phoneNumber, Set<SocialMediaAccount> socialMediaAccounts) {

        if (name != null && !name.isEmpty()) {
            this.name = validateName(name);
        }
        if (email != null && !email.isEmpty()) {
            this.email = validateEmail(email);
        }
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            this.phoneNumber = validatePhoneNumber(phoneNumber);
        }
        if (socialMediaAccounts != null && !socialMediaAccounts.isEmpty()) {
            this.socialMediaAccounts = socialMediaAccounts;
        }
    }

    public ContactId getId() {
        return id;
    }

    public void setId(ContactId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        Pattern emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
        if (!emailPattern.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Set<SocialMediaAccount> getSocialMediaAccounts() {
        return socialMediaAccounts;
    }

    public void addSocialMediaAccounts(SocialMediaAccount socialMediaAccounts) {
        this.socialMediaAccounts.add(socialMediaAccounts);
    }

    private String validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        return name;
    }

    private String validateEmail(String email) {
        if (!Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$").matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        return email;
    }

    private String validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be null or empty.");
        }
        return phoneNumber;
    }
}