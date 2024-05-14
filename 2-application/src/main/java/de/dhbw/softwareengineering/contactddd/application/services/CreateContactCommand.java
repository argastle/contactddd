package de.dhbw.softwareengineering.contactddd.application.services;

import de.dhbw.softwareengineering.contactddd.domain.values.Platform;

import java.util.Set;

public record CreateContactCommand(String name, String email, String phoneNumber, Set<SocialMediaAccountInfo> socialMediaAccountsInfos, Set<String> groups) {
    public record SocialMediaAccountInfo(String name, Platform platform){

    }
}
