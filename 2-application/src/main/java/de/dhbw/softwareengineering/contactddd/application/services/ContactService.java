package de.dhbw.softwareengineering.contactddd.application.services;

import de.dhbw.softwareengineering.contactddd.domain.entities.Contact;
import de.dhbw.softwareengineering.contactddd.domain.values.Platform;
import de.dhbw.softwareengineering.contactddd.domain.values.SocialMediaAccount;
import de.dhbw.softwareengineering.contactddd.domain.repositories.IContactRepository;
import de.dhbw.softwareengineering.contactddd.domain.values.ContactId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ContactService {

    private final IContactRepository contactRepository;

    @Autowired
    public ContactService(IContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public Contact createContact(CreateContactCommand command) {
        Set<SocialMediaAccount> socialMediaAccounts = Optional.ofNullable(command.socialMediaAccountsInfos())
                .orElse(Set.of())
                .stream()
                .map(info -> new SocialMediaAccount(info.name(), info.platform()))
                .collect(Collectors.toSet());

        Contact newContact = new Contact(command.name(), command.email(), command.phoneNumber(), socialMediaAccounts);
        return contactRepository.save(newContact);
    }

    public Optional<Contact> findContactById(String id) {
        return contactRepository.findById(new ContactId(id));
    }

    public Optional<List<Contact>> findContactByName(String name) {
        return contactRepository.findByName(name);
    }

    public List<Contact> findAllContacts() {
        return contactRepository.getAllUsers();
    }

    public Contact updateContact(String id, CreateContactCommand command) {
        ContactId contactId = new ContactId(id);
        Optional<Contact> contactOptional = contactRepository.findById(contactId);
        if (contactOptional.isPresent()) {
            Contact existingContact = contactOptional.get();
            Set<SocialMediaAccount> socialMediaAccounts = Optional.ofNullable(command.socialMediaAccountsInfos())
                    .orElse(Set.of())
                    .stream()
                    .map(info -> new SocialMediaAccount(info.name(), info.platform()))
                    .collect(Collectors.toSet());
            existingContact.updateContact(command.name(), command.email(), command.phoneNumber(), socialMediaAccounts);
            return contactRepository.save(existingContact);
        } else {
            throw new IllegalArgumentException("Contact with id " + id + " not found.");
        }
    }

    public void deleteContactById(String id) {
        contactRepository.deleteContact(new ContactId(id));
    }
}
