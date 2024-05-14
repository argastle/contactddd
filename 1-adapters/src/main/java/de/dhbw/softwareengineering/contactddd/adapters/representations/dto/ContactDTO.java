package de.dhbw.softwareengineering.contactddd.adapters.representations.dto;

import de.dhbw.softwareengineering.contactddd.domain.values.SocialMediaAccount;

import java.util.Set;

public record ContactDTO(String id, String name, String email, String phoneNumber, Set<SocialMediaAccountDTO> socialMediaAccounts, Set<String> groups) {

}
