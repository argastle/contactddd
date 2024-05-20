package de.dhbw.softwareengineering.contactddd.plugins.rest;

import de.dhbw.softwareengineering.contactddd.adapters.representations.contact.ContactDTO;
import de.dhbw.softwareengineering.contactddd.adapters.representations.contact.ContactEntityToContactDTOMapper;
import de.dhbw.softwareengineering.contactddd.application.services.ContactService;
import de.dhbw.softwareengineering.contactddd.application.services.CreateContactCommand;
import de.dhbw.softwareengineering.contactddd.domain.entities.Contact;
import de.dhbw.softwareengineering.contactddd.domain.exceptions.ContactNotFoundException;
import de.dhbw.softwareengineering.contactddd.domain.values.SpecialDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ContactControllerTest {

    @Mock
    private ContactService contactService;

    @Mock
    private ContactEntityToContactDTOMapper contactToDTO;

    @InjectMocks
    private ContactController contactController;

    private Contact contact;
    private ContactDTO contactDTO;
    private CreateContactCommand command;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        contact = new Contact("Max Mustermann", "max.mustermann@example.com", "+123456789", Set.of(), Set.of());
        contactDTO = new ContactDTO("1", "Max Mustermann", "max.mustermann@example.com", "+123456789", Set.of(), Set.of(), Set.of(), null, null);
        command = new CreateContactCommand("Max Mustermann", "max.mustermann@example.com", "+123456789", Set.of(), Set.of());

        when(contactToDTO.apply(contact)).thenReturn(contactDTO);
    }

    @Test
    void createContact() {
        when(contactService.createContact(command)).thenReturn(contact);

        ResponseEntity<ContactDTO> response = contactController.createContact(command);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(contactDTO, response.getBody());
    }

    @Test
    void getContactById() {
        when(contactService.findContactById("1")).thenReturn(Optional.of(contact));

        ResponseEntity<ContactDTO> response = contactController.getContactById("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(contactDTO, response.getBody());
    }

    @Test
    void getContactById_NotFound() {
        when(contactService.findContactById("1")).thenReturn(Optional.empty());

        ResponseEntity<ContactDTO> response = contactController.getContactById("1");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void getAllContacts() {
        when(contactService.findAllContacts()).thenReturn(List.of(contact));

        ResponseEntity<List<ContactDTO>> response = contactController.getAllContacts(null, null, null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(contactDTO, response.getBody().get(0));
    }

    @Test
    void searchContactsByName() {
        when(contactService.findContactByName("Max")).thenReturn(Optional.of(List.of(contact)));

        ResponseEntity<List<ContactDTO>> response = contactController.searchContactsByName("Max");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(contactDTO, response.getBody().get(0));
    }

    @Test
    void searchContactsByName_NotFound() {
        when(contactService.findContactByName("Max")).thenReturn(Optional.empty());

        ResponseEntity<List<ContactDTO>> response = contactController.searchContactsByName("Max");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void updateContact() {
        when(contactService.updateContact("1", command)).thenReturn(contact);

        ResponseEntity<ContactDTO> response = contactController.updateContact("1", command);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(contactDTO, response.getBody());
    }

    @Test
    void updateContact_NotFound() {
        when(contactService.updateContact("1", command)).thenThrow(new ContactNotFoundException("Contact with id 1 not found."));

        ResponseEntity<ContactDTO> response = contactController.updateContact("1", command);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void updateContactName() {
        when(contactService.updateContactName("1", "Max Mustermann")).thenReturn(contact);

        ResponseEntity<ContactDTO> response = contactController.updateContactName("1", "Max Mustermann");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(contactDTO, response.getBody());
    }

    @Test
    void updateContactName_NotFound() {
        doThrow(new ContactNotFoundException("Contact not found with ID: 1")).when(contactService).updateContactName("1", "Max Mustermann");

        ResponseEntity<ContactDTO> response = contactController.updateContactName("1", "Max Mustermann");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void updateContactEmail() {
        when(contactService.updateContactEmail("1", "max.mustermann@example.com")).thenReturn(contact);

        ResponseEntity<ContactDTO> response = contactController.updateContactEmail("1", "max.mustermann@example.com");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(contactDTO, response.getBody());
    }

    @Test
    void updateContactEmail_NotFound() {
        doThrow(new ContactNotFoundException("Contact not found with ID: 1")).when(contactService).updateContactEmail("1", "max.mustermann@example.com");

        ResponseEntity<ContactDTO> response = contactController.updateContactEmail("1", "max.mustermann@example.com");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void updateContactPhoneNumber() {
        when(contactService.updateContactPhoneNumber("1", "+123456789")).thenReturn(contact);

        ResponseEntity<ContactDTO> response = contactController.updateContactPhoneNumber("1", "+123456789");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(contactDTO, response.getBody());
    }

    @Test
    void updateContactPhoneNumber_NotFound() {
        doThrow(new ContactNotFoundException("Contact not found with ID: 1")).when(contactService).updateContactPhoneNumber("1", "+123456789");

        ResponseEntity<ContactDTO> response = contactController.updateContactPhoneNumber("1", "+123456789");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void addSpecialDateToContact() {
        SpecialDate specialDate = new SpecialDate(new Date(), "Birthday");
        when(contactService.addSpecialDateToContact("1", specialDate)).thenReturn(contact);

        ResponseEntity<ContactDTO> response = contactController.addSpecialDateToContact("1", specialDate);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(contactDTO, response.getBody());
    }

    @Test
    void addSpecialDateToContact_NotFound() {
        SpecialDate specialDate = new SpecialDate(new Date(), "Birthday");
        doThrow(new ContactNotFoundException("Contact not found with ID: 1")).when(contactService).addSpecialDateToContact("1", specialDate);

        ResponseEntity<ContactDTO> response = contactController.addSpecialDateToContact("1", specialDate);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void removeSpecialDateFromContact() {
        when(contactService.removeSpecialDateFromContact("1", "Birthday")).thenReturn(contact);

        ResponseEntity<ContactDTO> response = contactController.removeSpecialDateFromContact("1", "Birthday");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(contactDTO, response.getBody());
    }

    @Test
    void removeSpecialDateFromContact_NotFound() {
        doThrow(new ContactNotFoundException("Contact not found with ID: 1")).when(contactService).removeSpecialDateFromContact("1", "Birthday");

        ResponseEntity<ContactDTO> response = contactController.removeSpecialDateFromContact("1", "Birthday");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void deleteContactById() {
        when(contactService.findContactById("1")).thenReturn(Optional.of(contact));

        ResponseEntity<Void> response = contactController.deleteContactById("1");

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void deleteContactById_NotFound() {
        doThrow(new ContactNotFoundException("Contact not found with ID: 1")).when(contactService).deleteContactById("1");

        ResponseEntity<Void> response = contactController.deleteContactById("1");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

}
