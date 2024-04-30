package de.dhbw.softwareengineering.contactddd.plugins.rest;

import de.dhbw.softwareengineering.contactddd.domain.entities.Contact;
import de.dhbw.softwareengineering.contactddd.application.services.ContactService;
import de.dhbw.softwareengineering.contactddd.domain.values.ContactId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

  private final ContactService contactService;

  @Autowired
  public ContactController(ContactService contactService) {
    this.contactService = contactService;
  }

  @PostMapping
  public ResponseEntity<Contact> createContact(@RequestBody Contact contact) {
    Contact createdContact = contactService.createContact(contact);
    return new ResponseEntity<>(createdContact, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Contact> getContactById(@PathVariable String id) {
    return contactService.findContactById(id)
        .map(contact -> new ResponseEntity<>(contact, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @GetMapping
  public ResponseEntity<List<Contact>> getAllContacts() {
    List<Contact> contacts = contactService.findAllContacts();
    return new ResponseEntity<>(contacts, HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Contact> updateContact(@PathVariable String id,
      @RequestBody Contact contact) {
    if (!contactService.findContactById(id).isPresent()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    contact.setId(
        new ContactId(id));
    Contact updatedContact = contactService.updateContact(contact);
    return new ResponseEntity<>(updatedContact, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteContactById(@PathVariable String id) {
    if (!contactService.findContactById(id).isPresent()) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    contactService.deleteContactById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
