package de.dhbw.softwareengineering.contactddd.adapters.representations.mappers;


import de.dhbw.softwareengineering.contactddd.adapters.representations.dto.ContactDTO;
import de.dhbw.softwareengineering.contactddd.domain.entities.Contact;
import de.dhbw.softwareengineering.contactddd.domain.values.ContactId;

public class ContactMapper {

    public static ContactDTO toDTO(Contact contact) {
        if (contact == null) {
            return null;
        }
        return new ContactDTO(
                contact.getId().id(),
                contact.getName(),
                contact.getEmail(),
                contact.getPhoneNumber(),
                contact.getSocialMediaAccounts()
        );
    }

    public static Contact toEntity(ContactDTO contactDTO) {
        if (contactDTO == null) {
            return null;
        }
        Contact contact = new Contact(
                new ContactId(contactDTO.getId()),
                contactDTO.getName(),
                contactDTO.getEmail(),
                contactDTO.getPhoneNumber(),
                contactDTO.getSocialMediaAccounts()
        );
        return contact;
    }
}
