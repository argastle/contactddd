package de.dhbw.softwareengineering.contactddd.domain.entities;

import de.dhbw.softwareengineering.contactddd.domain.services.ContactValidator;
import de.dhbw.softwareengineering.contactddd.domain.values.ContactId;
import de.dhbw.softwareengineering.contactddd.domain.values.SocialMediaAccount;
import de.dhbw.softwareengineering.contactddd.domain.values.SpecialDate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Persistent;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

@Document(collection = "contacts")
@Persistent
public class Contact {
    @Id
    private ContactId contactId;

    private String name;

    private String email;

    private String phoneNumber;

    private Set<SocialMediaAccount> socialMediaAccounts;

    private Set<String> groups;

    private Set<SpecialDate> specialDates;

    private Date createdDate;

    @LastModifiedDate
    private Date lastModifiedDate;


    public Contact(ContactId contactId, String name, String email, String phoneNumber, Set<SocialMediaAccount> socialMediaAccounts, Set<String> groups, Set<SpecialDate> specialDates) {
        this.contactId = contactId;
        this.name = ContactValidator.validateName(name);
        this.email = ContactValidator.validateEmail(email);
        this.phoneNumber = ContactValidator.validatePhoneNumber(phoneNumber);
        this.socialMediaAccounts = socialMediaAccounts != null ? new HashSet<>(socialMediaAccounts) : new HashSet<>();
        this.groups = groups != null ? new HashSet<>(groups) : new HashSet<>();
        this.specialDates = specialDates != null ? new HashSet<>(specialDates) : new HashSet<>();
        this.createdDate = new Date();
    }

    public Contact(String name, String email, String phoneNumber, Set<SocialMediaAccount> socialMediaAccounts, Set<SpecialDate> specialDates) {
        this(new ContactId(), name, email, phoneNumber, socialMediaAccounts, new HashSet<>(), specialDates);
    }

    public Contact() {
    }

    public void updateContact(String name, String email, String phoneNumber, Set<SocialMediaAccount> socialMediaAccounts, Set<SpecialDate> specialDates) {
        if (name != null && !name.isEmpty()) {
            this.name = ContactValidator.validateName(name);
        }
        if (email != null && !email.isEmpty()) {
            this.email = ContactValidator.validateEmail(email);
        }
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            this.phoneNumber = ContactValidator.validatePhoneNumber(phoneNumber);
        }
        if (socialMediaAccounts != null && !socialMediaAccounts.isEmpty()) {
            this.socialMediaAccounts = socialMediaAccounts;
        }
        if (specialDates != null && !specialDates.isEmpty()) {
            this.specialDates = specialDates;
        }
    }

    public void setName(String newName) {
        this.name = ContactValidator.validateName(newName);
    }

    public void setEmail(String newEmail) {
        this.email = ContactValidator.validateEmail(newEmail);
    }

    public void setPhoneNumber(String newPhoneNumber) {
        this.phoneNumber = ContactValidator.validatePhoneNumber(newPhoneNumber);
    }
    public void addGroup(String group) {
        this.groups.add(group);
    }

    public void removeGroup(String group) {
        this.groups.remove(group);
    }

    public void addSpecialDate(SpecialDate specialDate) {
        this.specialDates.add(specialDate);
    }

    public void removeSpecialDateByDescription(String description) {
        this.specialDates.removeIf(specialDate -> specialDate.getDescription().equals(description));
    }

    public Set<SpecialDate> getSpecialDates() {
        return specialDates;
    }

    public void setSpecialDates(Set<SpecialDate> specialDates) {
        this.specialDates = specialDates;
    }

    public ContactId getContactId() {
        return contactId;
    }

    public void setContactId(ContactId contactId) {
        this.contactId = contactId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Set<SocialMediaAccount> getSocialMediaAccounts() {
        return socialMediaAccounts;
    }

    public void setSocialMediaAccounts(Set<SocialMediaAccount> socialMediaAccounts) {
        this.socialMediaAccounts = socialMediaAccounts;
    }

    public Set<String> getGroups() {
        return groups;
    }

    public void setGroups(Set<String> groups) {
        this.groups = groups;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }
}