package de.dhbw.softwareengineering.contactddd.domain.values;

public record SocialMediaAccount(String accountName, Platform platform) {

    public String getUrl() {
        return platform.getUrl() + accountName;
    }
}
