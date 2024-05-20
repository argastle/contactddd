package de.dhbw.softwareengineering.contactddd.domain.repositories;

import de.dhbw.softwareengineering.contactddd.domain.entities.Contact;
import de.dhbw.softwareengineering.contactddd.domain.values.ContactId;

import java.util.List;
import java.util.Optional;

public interface IContactRepository {

    Optional<Contact> findById(ContactId id);

    Optional<List<Contact>> findByName(String name);

    List<Contact> getAllContacts();

    Contact save(Contact contact);

    void deleteContact(ContactId id);
}
