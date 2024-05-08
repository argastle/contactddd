package de.dhbw.softwareengineering.contactddd.plugins.rest;

import de.dhbw.softwareengineering.contactddd.adapters.representations.dto.ContactDTO;
import de.dhbw.softwareengineering.contactddd.adapters.representations.mappers.ContactDTOToContactEntityMapper;
import de.dhbw.softwareengineering.contactddd.adapters.representations.mappers.ContactEntityToContactDTOMapper;
import de.dhbw.softwareengineering.contactddd.application.services.ContactService;
import de.dhbw.softwareengineering.contactddd.application.services.CreateContactCommand;
import de.dhbw.softwareengineering.contactddd.domain.entities.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactService contactService;

    private final ContactEntityToContactDTOMapper contactToDTO;
    private final ContactDTOToContactEntityMapper contactDTOToEntityMapper;

    @Autowired
    public ContactController(final ContactService contactService, final ContactEntityToContactDTOMapper contactToDTO, ContactDTOToContactEntityMapper contactDTOToEntityMapper) {
        this.contactService = contactService;
        this.contactToDTO = contactToDTO;
        this.contactDTOToEntityMapper = contactDTOToEntityMapper;
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
    public ResponseEntity<List<ContactDTO>> getAllContacts() {
        List<ContactDTO> contacts = contactService.findAllContacts()
                .stream()
                .map(contactToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactDTO> updateContact(@PathVariable String id, @RequestBody ContactDTO contactDTO) {
        return contactService.findContactById(id)
                .map(existingContact -> {
                    Contact updatedContact = contactDTOToEntityMapper.apply(contactDTO);
                    updatedContact.setId(existingContact.getId());
                    ContactDTO updatedContactDTO = contactToDTO.apply(contactService.updateContact(updatedContact));
                    return new ResponseEntity<>(updatedContactDTO, HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
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
