package de.dhbw.softwareengineering.contactddd.domain.values;

public class SocialMediaAccount {
    private String accountName;
    private Platform platform;

    public SocialMediaAccount(String accountName, Platform platform) {
        this.accountName = accountName;
        this.platform = platform;
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

    public String getUrl() {
        return platform.getUrl() + accountName;
    }


}
