package de.dhbw.softwareengineering.contactddd.adapters.representations.dto;

import java.util.Date;
import java.util.Set;

public final class ContactDTO {
    private String id;
    private String name;
    private String email;
    private String phoneNumber;
    private Set<SocialMediaAccountDTO> socialMediaAccounts;
    private Set<String> groups;
    private Set<SpecialDateDTO> specialDates;
    private Date createdDate;
    private Date lastModifiedDate;

    public ContactDTO(String id, String name, String email, String phoneNumber, Set<SocialMediaAccountDTO> socialMediaAccounts, Set<String> groups, Set<SpecialDateDTO> specialDates, Date createdDate, Date lastModifiedDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.socialMediaAccounts = socialMediaAccounts;
        this.groups = groups;
        this.specialDates = specialDates;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
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

    public Set<SocialMediaAccountDTO> getSocialMediaAccounts() {
        return socialMediaAccounts;
    }

    public void setSocialMediaAccounts(Set<SocialMediaAccountDTO> socialMediaAccounts) {
        this.socialMediaAccounts = socialMediaAccounts;
    }

    public Set<String> getGroups() {
        return groups;
    }

    public void setGroups(Set<String> groups) {
        this.groups = groups;
    }

    public Set<SpecialDateDTO> getSpecialDates() {
        return specialDates;
    }

    public void setSpecialDates(Set<SpecialDateDTO> specialDates) {
        this.specialDates = specialDates;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
