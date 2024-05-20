package de.dhbw.softwareengineering.contactddd.domain.values;

public class SocialMediaAccount {

    private String accountName;
    private Platform platform;

    public SocialMediaAccount(String accountName, Platform platform) {
        this.accountName = validateAccountName(accountName);
        this.platform = validatePlatform(platform);
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = validateAccountName(accountName);
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = validatePlatform(platform);
    }

    public String getUrl() {
        return platform.getUrl() + accountName;
    }

    private String validateAccountName(String accountName) {
        if (accountName == null || accountName.isEmpty()) {
            throw new IllegalArgumentException("Account name cannot be null or empty.");
        }
        return accountName;
    }

    private Platform validatePlatform(Platform platform) {
        if (platform == null) {
            throw new IllegalArgumentException("Platform cannot be null.");
        }
        return platform;
    }
}
