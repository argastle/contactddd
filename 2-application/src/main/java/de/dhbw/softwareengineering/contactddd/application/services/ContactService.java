package de.dhbw.softwareengineering.contactddd.application.services;

import de.dhbw.softwareengineering.contactddd.domain.entities.Contact;
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
        Contact newContact = new Contact(command.getName(), command.getEmail(), command.getPhoneNumber(), socialMediaAccounts, command.getGroups(), specialDates);
        return contactRepository.save(newContact);
    }

    public void updateContactName(String contactId, String newName) {
        Contact contact = getContactOrThrow(contactId);
        contact.setName(newName);
        contactRepository.save(contact);
    }

    public void updateContactEmail(String contactId, String newEmail) {
        Contact contact = getContactOrThrow(contactId);
        contact.setEmail(newEmail);
        contactRepository.save(contact);
    }

    public void updateContactPhoneNumber(String contactId, String newPhoneNumber) {
        Contact contact = getContactOrThrow(contactId);
        contact.setPhoneNumber(newPhoneNumber);
        contactRepository.save(contact);
    }

    public void addGroupToContact(String contactId, String group) {
        Contact contact = getContactOrThrow(contactId);
        contact.addGroup(group);
        contactRepository.save(contact);
    }

    public void removeGroupFromContact(String contactId, String group) {
        Contact contact = getContactOrThrow(contactId);
        contact.removeGroup(group);
        contactRepository.save(contact);
    }

    public void addSpecialDateToContact(String contactId, SpecialDate specialDate) {
        Contact contact = getContactOrThrow(contactId);
        contact.addSpecialDate(specialDate);
        contactRepository.save(contact);
    }

    public void removeSpecialDateFromContact(String contactId, String description) {
        Contact contact = getContactOrThrow(contactId);
        contact.removeSpecialDateByDescription(description);
        contactRepository.save(contact);
    }


    public Optional<Contact> findContactById(String id) {
        return contactRepository.findById(new ContactId(id));
    }

    public Optional<List<Contact>> findContactByName(String name) {
        return contactRepository.findByName(name);
    }

    public List<Contact> findContactsByAnyGroup(List<String> group) {
        return contactRepository.findContactsByAnyGroup(group);
    }

    public List<Contact> findContactsByAllGroups(List<String> groups) {
        return contactRepository.findContactsByAllGroups(groups);
    }

    public List<Contact> findAllContacts() {
        return contactRepository.getAllContacts();
    }

    public Contact updateContact(String id, CreateContactCommand command) {
        ContactId contactId = new ContactId(id);
        Optional<Contact> contactOptional = contactRepository.findById(contactId);
        if (contactOptional.isPresent()) {
            Contact existingContact = contactOptional.get();
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
            existingContact.updateContact(command.getName(), command.getEmail(), command.getPhoneNumber(), socialMediaAccounts, command.getGroups(), specialDates);
            return contactRepository.save(existingContact);
        } else {
            throw new IllegalArgumentException("Contact with id " + id + " not found.");
        }
    }

    public void deleteContactById(String id) {
        contactRepository.deleteContact(new ContactId(id));
    }

    private Contact getContactOrThrow(String contactId) {
        return contactRepository.findById(new ContactId(contactId)).orElseThrow(() -> new IllegalArgumentException("Contact not found with ID: " + contactId));
    }

}
