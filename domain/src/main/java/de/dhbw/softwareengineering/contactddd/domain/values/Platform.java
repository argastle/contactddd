package de.dhbw.softwareengineering.contactddd.domain.values;

public enum Platform {
    INSTAGRAM("https://www.instagram.com/"),
    YOUTUBE("https://www.youtube.com/@");

    private final String url;

    Platform(String url) {
        this.url = url;
    }

    public String getUrl(String accountName) {
        return url + accountName;
    }
}
