package de.dhbw.softwareengineering.contactddd.adapters.representations.mappers;


import de.dhbw.softwareengineering.contactddd.adapters.representations.dto.ContactDTO;
import de.dhbw.softwareengineering.contactddd.domain.entities.Contact;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ContactEntityToContactDTOMapper implements Function<Contact, ContactDTO> {

    @Override
    public ContactDTO apply(final Contact contact) {
        return map(contact);
    }

    private ContactDTO map(final Contact contact) {
        return new ContactDTO(
                contact.getId().id(),
                contact.getName(),
                contact.getEmail(),
                contact.getPhoneNumber(),
                new SocialMediaAccountEntitySetToSocialMediaAccountDTOSetMapper().map(contact.getSocialMediaAccounts())
        );
    }
}






