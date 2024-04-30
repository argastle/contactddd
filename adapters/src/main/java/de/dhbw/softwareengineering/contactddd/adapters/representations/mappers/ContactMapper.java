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
        contact.getId().toString(),
        contact.getName(),
        contact.getEmail(),
        contact.getPhoneNumber()
    );
  }

  public static Contact toEntity(ContactDTO contactDTO) {
    if (contactDTO == null) {
      return null;
    }
    Contact contact = new Contact(
        contactDTO.getName(),
        contactDTO.getEmail(),
        contactDTO.getPhoneNumber()
    );
    // Setzen der ID nur, wenn sie im DTO vorhanden ist.
    if (contactDTO.getId() != null && !contactDTO.getId().isEmpty()) {
      contact.setId(new ContactId(contactDTO.getId()));
    }
    return contact;
  }
}
