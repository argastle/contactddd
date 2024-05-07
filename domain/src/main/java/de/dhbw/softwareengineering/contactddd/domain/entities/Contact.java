package de.dhbw.softwareengineering.contactddd.domain.entities;

import de.dhbw.softwareengineering.contactddd.domain.values.ContactId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "contacts")
public class Contact {
    @Id
    private ContactId id;

    private String name;

    private String email;

    private String phoneNumber;

    private Set<SocialMediaAccount> socialMediaAccounts;

    public Contact(ContactId id, String name, String email, String phoneNumber, Set<SocialMediaAccount> socialMediaAccounts) {
        //TODO Validierung
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.socialMediaAccounts = socialMediaAccounts;
    }

    public Contact(String name, String email, String phoneNumber, Set<SocialMediaAccount> socialMediaAccounts) {
        this(new ContactId(), name, email, phoneNumber, socialMediaAccounts);
    }

    public Contact() {
    }

    public void updateContact(String name, String email, String phoneNumber, Set<SocialMediaAccount> socialMediaAccounts) {
        if (!(name == null && name.isEmpty())) {
            this.name = name;
        }
        //TODO restliche validierung

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
        //TODO Email Validierung
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
}