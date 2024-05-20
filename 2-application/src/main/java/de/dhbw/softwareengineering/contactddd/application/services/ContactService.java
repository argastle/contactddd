package de.dhbw.softwareengineering.contactddd.application.services;

import de.dhbw.softwareengineering.contactddd.domain.entities.Contact;
import de.dhbw.softwareengineering.contactddd.domain.exceptions.ContactNotFoundException;
import de.dhbw.softwareengineering.contactddd.domain.values.SocialMediaAccount;
import de.dhbw.softwareengineering.contactddd.domain.repositories.IContactRepository;
import de.dhbw.softwareengineering.contactddd.domain.values.ContactId;
import de.dhbw.softwareengineering.contactddd.domain.values.SpecialDate;
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
        Set<SocialMediaAccount> socialMediaAccounts = Optional.ofNullable(command.getSocialMediaAccountsInfos())
                .orElse(Set.of())
                .stream()
                .map(info -> new SocialMediaAccount(info.getName(), info.getPlatform()))
                .collect(Collectors.toSet());
        Set<SpecialDate> specialDates = Optional.ofNullable(command.getSpecialDatesInfos())
                .orElse(Set.of())
                .stream()
                .map(info -> new SpecialDate(info.getDate(), info.getDescription()))
                .collect(Collectors.toSet());
        Contact newContact = new Contact(command.getName(), command.getEmail(), command.getPhoneNumber(), socialMediaAccounts, specialDates);
        return contactRepository.save(newContact);
    }

    public Contact updateContactName(String contactId, String newName) {
        Contact contact = getContactOrThrow(contactId);
        contact.setName(newName);
        return contactRepository.save(contact);
    }

    public Contact updateContactEmail(String contactId, String newEmail) {
        Contact contact = getContactOrThrow(contactId);
        contact.setEmail(newEmail);
        return contactRepository.save(contact);
    }

    public Contact updateContactPhoneNumber(String contactId, String newPhoneNumber) {
        Contact contact = getContactOrThrow(contactId);
        contact.setPhoneNumber(newPhoneNumber);
        return contactRepository.save(contact);
    }

    public Contact addSpecialDateToContact(String contactId, SpecialDate specialDate) {
        Contact contact = getContactOrThrow(contactId);
        contact.addSpecialDate(specialDate);
        return contactRepository.save(contact);
    }

    public Contact removeSpecialDateFromContact(String contactId, String description) {
        Contact contact = getContactOrThrow(contactId);
        contact.removeSpecialDateByDescription(description);
        return contactRepository.save(contact);
    }


    public Optional<Contact> findContactById(String id) {
        return contactRepository.findById(new ContactId(id));
    }

    public Optional<List<Contact>> findContactByName(String name) {
        return contactRepository.findByName(name);
    }

    public List<Contact> findAllContacts() {
        return contactRepository.getAllContacts();
    }

    public Contact updateContact(String id, CreateContactCommand command) {

        Contact contact = getContactOrThrow(id);
        Set<SocialMediaAccount> socialMediaAccounts = Optional.ofNullable(command.getSocialMediaAccountsInfos())
                .orElse(Set.of())
                .stream()
                .map(info -> new SocialMediaAccount(info.getName(), info.getPlatform()))
                .collect(Collectors.toSet());
        Set<SpecialDate> specialDates = Optional.ofNullable(command.getSpecialDatesInfos())
                .orElse(Set.of())
                .stream()
                .map(info -> new SpecialDate(info.getDate(), info.getDescription()))
                .collect(Collectors.toSet());
        contact.updateContact(command.getName(), command.getEmail(), command.getPhoneNumber(), socialMediaAccounts, specialDates);
        return contactRepository.save(contact);
    }

    public void deleteContactById(String contactId) {
        Contact contact = getContactOrThrow(contactId);
        contactRepository.deleteContact(contact.getContactId());
    }

    private Contact getContactOrThrow(String contactId) {
        return contactRepository.findById(new ContactId(contactId)).orElseThrow(() -> new ContactNotFoundException("Contact not found with ID: " + contactId));
    }

}
