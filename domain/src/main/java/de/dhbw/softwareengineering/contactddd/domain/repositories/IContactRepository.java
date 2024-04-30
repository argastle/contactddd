package de.dhbw.softwareengineering.contactddd.domain.repositories;

import de.dhbw.softwareengineering.contactddd.domain.entities.Contact;
import de.dhbw.softwareengineering.contactddd.domain.values.ContactId;
import java.util.Optional;

public interface IContactRepository {

  // Sucht einen Kontakt anhand seiner ID
  Optional<Contact> findById(ContactId id);

  // Sucht Kontakte anhand des Namens (dies könnte eine Liste von Kontakten zurückgeben)
  Optional<Contact> findByName(String name);
}
