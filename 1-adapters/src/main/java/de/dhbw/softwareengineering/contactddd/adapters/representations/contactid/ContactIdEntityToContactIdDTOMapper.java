package de.dhbw.softwareengineering.contactddd.adapters.representations.contactid;

import de.dhbw.softwareengineering.contactddd.domain.values.ContactId;

import java.util.function.Function;

public class ContactIdEntityToContactIdDTOMapper implements Function<ContactId, ContactIdDTO> {
    @Override
    public ContactIdDTO apply(final ContactId contactId) {
        return map(contactId);
    }

    public ContactIdDTO map(ContactId contactId) {
        return new ContactIdDTO(contactId.getId());
    }
}
