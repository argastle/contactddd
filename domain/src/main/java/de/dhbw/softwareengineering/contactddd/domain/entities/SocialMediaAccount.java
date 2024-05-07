package de.dhbw.softwareengineering.contactddd.domain.entities;

public class SocialMediaAccount {

    private String accountName;
    private String platform;

    public SocialMediaAccount(String accountName, String platform) {
        this.accountName = accountName;
        this.platform = platform;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }
}
