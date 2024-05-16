package de.dhbw.softwareengineering.contactddd.application.services;

import de.dhbw.softwareengineering.contactddd.domain.values.Platform;
import de.dhbw.softwareengineering.contactddd.domain.values.SpecialDate;

import java.util.Date;
import java.util.Set;

public final class CreateContactCommand {
    private String name;
    private String email;
    private String phoneNumber;
    private Set<SocialMediaAccountInfo> socialMediaAccountsInfos;
    private Set<String> groups;
    private Set<SpecialDateInfo> specialDatesInfos;

    public CreateContactCommand(String name, String email, String phoneNumber, Set<SocialMediaAccountInfo> socialMediaAccountsInfos, Set<String> groups, Set<SpecialDateInfo> specialDatesInfos) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.socialMediaAccountsInfos = socialMediaAccountsInfos;
        this.groups = groups;
        this.specialDatesInfos = specialDatesInfos;
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

    public Set<SocialMediaAccountInfo> getSocialMediaAccountsInfos() {
        return socialMediaAccountsInfos;
    }

    public void setSocialMediaAccountsInfos(Set<SocialMediaAccountInfo> socialMediaAccountsInfos) {
        this.socialMediaAccountsInfos = socialMediaAccountsInfos;
    }

    public Set<String> getGroups() {
        return groups;
    }

    public void setGroups(Set<String> groups) {
        this.groups = groups;
    }

    public Set<SpecialDateInfo> getSpecialDatesInfos() {
        return specialDatesInfos;
    }

    public void setSpecialDatesInfos(Set<SpecialDateInfo> specialDatesInfos) {
        this.specialDatesInfos = specialDatesInfos;
    }

    public static final class SocialMediaAccountInfo {
        private String name;
        private Platform platform;

        public SocialMediaAccountInfo(String name, Platform platform) {
            this.name = name;
            this.platform = platform;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Platform getPlatform() {
            return platform;
        }

        public void setPlatform(Platform platform) {
            this.platform = platform;
        }
    }

    public static final class SpecialDateInfo {
        private String description;
        private Date date;

        public SpecialDateInfo(String description, Date date) {
            this.description = description;
            this.date = date;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }
    }
}
