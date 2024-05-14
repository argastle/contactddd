package de.dhbw.softwareengineering.contactddd.adapters.representations.mappers;


import de.dhbw.softwareengineering.contactddd.adapters.representations.dto.ContactDTO;
import de.dhbw.softwareengineering.contactddd.adapters.representations.dto.SocialMediaAccountDTO;
import de.dhbw.softwareengineering.contactddd.application.services.CreateContactCommand;
import de.dhbw.softwareengineering.contactddd.domain.entities.Contact;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class ContactEntityToContactDTOMapper implements Function<Contact, ContactDTO> {

    @Override
    public ContactDTO apply(final Contact contact) {
        return map(contact);
    }

    public ContactDTO map(Contact contact) {
        Set<SocialMediaAccountDTO> socialMediaAccounts = contact.getSocialMediaAccounts()
                .stream()
                .map(new SocialMediaAccountEntityToSocialMediaAccountDTOMapper()::map)
                .collect(Collectors.toSet());
        return new ContactDTO(contact.getId().id(), contact.getName(), contact.getEmail(), contact.getPhoneNumber(), socialMediaAccounts, contact.getGroups());
    }
}






