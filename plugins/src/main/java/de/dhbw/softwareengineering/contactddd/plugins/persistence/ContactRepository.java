package de.dhbw.softwareengineering.contactddd.plugins.persistence;

import de.dhbw.softwareengineering.contactddd.adapters.representations.dto.ContactDTO;
import de.dhbw.softwareengineering.contactddd.adapters.representations.mappers.ContactMapper;
import de.dhbw.softwareengineering.contactddd.domain.entities.Contact;
import de.dhbw.softwareengineering.contactddd.domain.repositories.IContactRepository;
import de.dhbw.softwareengineering.contactddd.domain.values.ContactId;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class ContactRepository implements IContactRepository {

    DataContactRepository dataContactRepository;

    @Autowired
    public ContactRepository(DataContactRepository dataContactRepository) {
        this.dataContactRepository = dataContactRepository;
    }

    @Override
    public Optional<Contact> findById(ContactId id) {
        return dataContactRepository.findById(id);
    }

    @Override
    public Optional<List<Contact>> findByName(String name) {
        return dataContactRepository.findByName(name);
    }

    @Override
    public List<Contact> getAllUsers() {
        return dataContactRepository.findAll();
    }

    @Override
    public Contact save(Contact contact) {

        return dataContactRepository.save(contact);
    }

    @Override
    public void deleteContact(String contactId) {

    }


/**
 * In dem package landen die ganzen Bridges welche benutzt werden zur kommunikation
 * zwischen den schichten.
 */
}
