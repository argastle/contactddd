package de.dhbw.softwareengineering.contactddd.application.services;

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

  public Contact createContact(Contact contact) {
    return contactRepository.save(contact);
  }

  public Optional<Contact> findContactById(String id) {
    return contactRepository.findById(new ContactId(id));
  }

  public List<Contact> findAllContacts() {
    return contactRepository.findAll();
  }

  public Contact updateContact(Contact contact) {
    return contactRepository.save(contact);
  }

  public void deleteContactById(String id) {
    contactRepository.deleteById(id);
  }
}
