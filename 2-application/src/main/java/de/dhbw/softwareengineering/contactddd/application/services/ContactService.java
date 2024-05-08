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
        Set<SocialMediaAccount> socialMediaAccounts = command.socialMediaAccountsInfos()
                .stream()
                .map(info -> new SocialMediaAccount(info.name(), Platform.valueOf(info.platform())))
                .collect(Collectors.toSet());

        Contact newContact = new Contact(command.name(), command.email(), command.phoneNumber(), socialMediaAccounts);
        return contactRepository.save(newContact);
    }

    public Optional<Contact> findContactById(String id) {
        return contactRepository.findById(new ContactId(id));
    }

    public List<Contact> findAllContacts() {
        return contactRepository.getAllUsers();
    }

    public Contact updateContact(Contact contact) {
        return contactRepository.save(contact);
    }

    public void deleteContactById(String id) {
        contactRepository.deleteContact(new ContactId(id));
    }
}
