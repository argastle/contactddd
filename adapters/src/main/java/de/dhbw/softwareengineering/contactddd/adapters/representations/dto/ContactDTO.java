package de.dhbw.softwareengineering.contactddd.adapters.representations.dto;

import de.dhbw.softwareengineering.contactddd.domain.entities.SocialMediaAccount;

import java.util.Set;

public class ContactDTO {

    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private Set<SocialMediaAccount> socialMediaAccounts;

    public ContactDTO(String id, String name, String email, String phoneNumber, Set<SocialMediaAccount> socialMediaAccounts) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.socialMediaAccounts = socialMediaAccounts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public void setSocialMediaAccounts(Set<SocialMediaAccount> socialMediaAccounts) {
        this.socialMediaAccounts = socialMediaAccounts;
    }
}
