package de.dhbw.softwareengineering.contactddd.plugins.rest;

import de.dhbw.softwareengineering.contactddd.adapters.representations.dto.ContactDTO;
import de.dhbw.softwareengineering.contactddd.adapters.representations.mappers.ContactEntityToContactDTOMapper;
import de.dhbw.softwareengineering.contactddd.application.services.ContactService;
import de.dhbw.softwareengineering.contactddd.application.services.CreateContactCommand;
import de.dhbw.softwareengineering.contactddd.domain.entities.Contact;
import de.dhbw.softwareengineering.contactddd.domain.values.SpecialDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactService contactService;

    private final ContactEntityToContactDTOMapper contactToDTO;

    @Autowired
    public ContactController(final ContactService contactService, final ContactEntityToContactDTOMapper contactToDTO) {
        this.contactService = contactService;
        this.contactToDTO = contactToDTO;
    }

    @PostMapping
    public ResponseEntity<ContactDTO> createContact(@RequestBody CreateContactCommand command) {
        Contact createdContact = contactService.createContact(command);
        return new ResponseEntity<>(contactToDTO.apply(createdContact), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactDTO> getContactById(@PathVariable String id) {
        return contactService.findContactById(id)
                .map(contact -> new ResponseEntity<>(contactToDTO.apply(contact), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<ContactDTO>> getAllContacts(
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String order,
            @RequestParam(required = false) String specialDateDescription) {

        Comparator<Contact> comparator;
        if ("name".equalsIgnoreCase(sortBy)) {
            comparator = Comparator.comparing(Contact::getName);
        } else if ("email".equalsIgnoreCase(sortBy)) {
            comparator = Comparator.comparing(Contact::getEmail);
        } else if ("phoneNumber".equalsIgnoreCase(sortBy)) {
            comparator = Comparator.comparing(Contact::getPhoneNumber);
        } else if ("createdDate".equalsIgnoreCase(sortBy)) {
            comparator = Comparator.comparing(Contact::getCreatedDate);
        } else if ("lastModifiedDate".equalsIgnoreCase(sortBy)) {
            comparator = Comparator.comparing(Contact::getLastModifiedDate);
        } else if ("specialDate".equalsIgnoreCase(sortBy) && specialDateDescription != null) {
            comparator = Comparator.comparing(contact -> contact.getSpecialDates().stream()
                    .filter(specialDate -> specialDate.getDescription().equalsIgnoreCase(specialDateDescription))
                    .findFirst()
                    .map(SpecialDate::getDate)
                    .orElse(new Date(Long.MAX_VALUE)));
        } else {
            comparator = Comparator.comparing(contact -> contact.getContactId().id());
        }

        if ("desc".equalsIgnoreCase(order)) {
            comparator = comparator.reversed();
        }

        List<ContactDTO> contacts = contactService.findAllContacts()
                .stream()
                .sorted(comparator)
                .map(contactToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }


    @GetMapping("/search")
    public ResponseEntity<List<ContactDTO>> searchContactsByName(@RequestParam String name) {
        Optional<List<Contact>> contacts = contactService.findContactByName(name);
        return contacts.map(contactList -> {
            List<ContactDTO> contactDTOs = contactList.stream()
                    .map(contactToDTO)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(contactDTOs, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @GetMapping("/groups")
    public ResponseEntity<List<ContactDTO>> getContactsByGroups(@RequestParam List<String> groups, @RequestParam(defaultValue = "or") String mode) {
        List<Contact> contacts;
        if ("and".equalsIgnoreCase(mode)) {
            contacts = contactService.findContactsByAllGroups(groups);
        } else {
            contacts = contactService.findContactsByAnyGroup(groups);
        }
        List<ContactDTO> contactDTOs = contacts.stream()
                .map(contactToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(contactDTOs, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactDTO> updateContact(@PathVariable String id, @RequestBody CreateContactCommand command) {
        try {
            Contact updatedContact = contactService.updateContact(id, command);
            return new ResponseEntity<>(contactToDTO.apply(updatedContact), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{contactId}/updateName")
    public ResponseEntity<Void> updateContactName(@PathVariable String contactId, @RequestParam String newName) {
        contactService.updateContactName(contactId, newName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{contactId}/updateEmail")
    public ResponseEntity<Void> updateContactEmail(@PathVariable String contactId, @RequestParam String newEmail) {
        contactService.updateContactEmail(contactId, newEmail);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{contactId}/updatePhoneNumber")
    public ResponseEntity<Void> updateContactPhoneNumber(@PathVariable String contactId, @RequestParam String newPhoneNumber) {
        contactService.updateContactPhoneNumber(contactId, newPhoneNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{contactId}/addGroup")
    public ResponseEntity<Void> addGroupToContact(@PathVariable String contactId, @RequestParam String group) {
        contactService.addGroupToContact(contactId, group);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{contactId}/removeGroup")
    public ResponseEntity<Void> removeGroupFromContact(@PathVariable String contactId, @RequestParam String group) {
        contactService.removeGroupFromContact(contactId, group);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{contactId}/addSpecialDate")
    public ResponseEntity<Void> addSpecialDateToContact(@PathVariable String contactId, @RequestBody SpecialDate specialDate) {
        contactService.addSpecialDateToContact(contactId, specialDate);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{contactId}/removeSpecialDate")
    public ResponseEntity<Void> removeSpecialDateFromContact(@PathVariable String contactId, @RequestParam String description) {
        contactService.removeSpecialDateFromContact(contactId, description);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContactById(@PathVariable String id) {
        if (contactService.findContactById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        contactService.deleteContactById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
