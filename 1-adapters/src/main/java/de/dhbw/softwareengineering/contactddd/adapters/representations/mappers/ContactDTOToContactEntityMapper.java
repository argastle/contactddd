package de.dhbw.softwareengineering.contactddd.adapters.representations.mappers;

import de.dhbw.softwareengineering.contactddd.adapters.representations.dto.ContactDTO;
import de.dhbw.softwareengineering.contactddd.domain.entities.Contact;
import de.dhbw.softwareengineering.contactddd.domain.values.ContactId;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ContactDTOToContactEntityMapper implements Function<ContactDTO, Contact> {

    @Override
    public Contact apply(ContactDTO contactDTO) {
        return map(contactDTO);
    }

    public Contact map(ContactDTO contactDTO) {
        if (contactDTO == null) {
            return null;
        }
        return new Contact(
                new ContactId(contactDTO.id()),
                contactDTO.name(),
                contactDTO.email(),
                contactDTO.phoneNumber(),
                new SocialMediaAccountDTOSetToSocialMediaAccountEntitySetMapper().map(contactDTO.socialMediaAccounts())
        );
    }
}