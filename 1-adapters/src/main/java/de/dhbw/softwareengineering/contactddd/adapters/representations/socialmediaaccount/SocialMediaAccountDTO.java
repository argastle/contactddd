package de.dhbw.softwareengineering.contactddd.adapters.representations.socialmediaaccount;

import de.dhbw.softwareengineering.contactddd.domain.values.Platform;

public class SocialMediaAccountDTO {

    String accountName;
    Platform platform;
    String url;

    public SocialMediaAccountDTO(String accountName, Platform platform, String url) {
        this.accountName = accountName;
        this.platform = platform;
        this.url = url;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }
}
