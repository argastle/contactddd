package de.dhbw.softwareengineering.contactddd.application.services;

import java.util.Set;

public record CreateContactCommand(String name, String email, String phoneNumber, Set<SocialMediaAccountInfo> socialMediaAccountsInfos) {
    public record SocialMediaAccountInfo(String name, String platform){

    }
}
