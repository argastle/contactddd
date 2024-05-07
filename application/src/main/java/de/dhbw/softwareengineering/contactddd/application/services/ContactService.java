package de.dhbw.softwareengineering.contactddd.application.services;

import de.dhbw.softwareengineering.contactddd.adapters.representations.dto.ContactDTO;
import de.dhbw.softwareengineering.contactddd.adapters.representations.mappers.ContactMapper;
import de.dhbw.softwareengineering.contactddd.domain.entities.Contact;
import de.dhbw.softwareengineering.contactddd.domain.repositories.IContactRepository;
import de.dhbw.softwareengineering.contactddd.domain.values.ContactId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    private final IContactRepository contactRepository;

    @Autowired
    public ContactService(IContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public ContactDTO createContact(ContactDTO contactDTO) {
        Contact contact = new Contact(contactDTO.getName(), contactDTO.getEmail(), contactDTO.getPhoneNumber(), contactDTO.getSocialMediaAccounts());
        return ContactMapper.toDTO(contactRepository.save(contact));
    }

    public Optional<ContactDTO> findContactById(String id) {
        Optional<Contact> contact = contactRepository.findById(new ContactId(id));
        return Optional.of(ContactMapper.toDTO(contact.get()));
    }

    public List<ContactDTO> findAllContacts() {
        List<Contact> contacts = contactRepository.getAllUsers();
        return contacts.stream().map(ContactMapper::toDTO).toList();
    }

    public ContactDTO updateContact(ContactDTO contactDTO) {
        Optional<Contact> contact = contactRepository.findById(new ContactId(contactDTO.getId()));
        contact.get().updateContact(contactDTO.getName(), contactDTO.getEmail(), contactDTO.getPhoneNumber(), contactDTO.getSocialMediaAccounts());
        return ContactMapper.toDTO(contactRepository.save(contact.get()));
    }

    public void deleteContactById(String id) {
        contactRepository.deleteContact(id);
    }
}
