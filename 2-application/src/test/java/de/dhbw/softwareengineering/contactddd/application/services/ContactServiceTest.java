package de.dhbw.softwareengineering.contactddd.application.services;

import de.dhbw.softwareengineering.contactddd.domain.entities.Contact;
import de.dhbw.softwareengineering.contactddd.domain.exceptions.ContactNotFoundException;
import de.dhbw.softwareengineering.contactddd.domain.repositories.IContactRepository;
import de.dhbw.softwareengineering.contactddd.domain.values.ContactId;
import de.dhbw.softwareengineering.contactddd.domain.values.SocialMediaAccount;
import de.dhbw.softwareengineering.contactddd.domain.values.SpecialDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ContactServiceTest {

    @Mock
    private IContactRepository contactRepository;

    @InjectMocks
    private ContactService contactService;

    private Contact contact;
    private CreateContactCommand command;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        contact = new Contact("Max Mustermann", "max.mustermann@example.com", "+123456789", Set.of(), Set.of());
        command = new CreateContactCommand("Max Mustermann", "max.mustermann@example.com", "+123456789", Set.of(), Set.of());
    }

    @Test
    void createContact() {
        when(contactRepository.save(any(Contact.class))).thenReturn(contact);

        Contact createdContact = contactService.createContact(command);

        assertNotNull(createdContact);
        assertEquals("Max Mustermann", createdContact.getName());
        assertEquals("max.mustermann@example.com", createdContact.getEmail());
        verify(contactRepository, times(1)).save(any(Contact.class));
    }

    @Test
    void updateContact() {
        when(contactRepository.findById(any(ContactId.class))).thenReturn(Optional.of(contact));
        when(contactRepository.save(any(Contact.class))).thenReturn(contact);

        Contact updatedContact = contactService.updateContact("1", command);

        assertNotNull(updatedContact);
        assertEquals("Max Mustermann", updatedContact.getName());
        assertEquals("max.mustermann@example.com", updatedContact.getEmail());
        verify(contactRepository, times(1)).findById(any(ContactId.class));
        verify(contactRepository, times(1)).save(any(Contact.class));
    }

    @Test
    void updateContact_NotFound() {
        when(contactRepository.findById(any(ContactId.class))).thenReturn(Optional.empty());

        assertThrows(ContactNotFoundException.class, () -> contactService.updateContact("1", command));
        verify(contactRepository, times(1)).findById(any(ContactId.class));
        verify(contactRepository, times(0)).save(any(Contact.class));
    }

    @Test
    void updateContactName() {
        when(contactRepository.findById(any(ContactId.class))).thenReturn(Optional.of(contact));
        when(contactRepository.save(any(Contact.class))).thenReturn(contact);

        Contact updatedContact = contactService.updateContactName("1", "Max Mustermann");

        assertNotNull(updatedContact);
        assertEquals("Max Mustermann", updatedContact.getName());
        verify(contactRepository, times(1)).findById(any(ContactId.class));
        verify(contactRepository, times(1)).save(any(Contact.class));
    }

    @Test
    void updateContactName_NotFound() {
        when(contactRepository.findById(any(ContactId.class))).thenReturn(Optional.empty());

        assertThrows(ContactNotFoundException.class, () -> contactService.updateContactName("1", "Max Mustermann"));
        verify(contactRepository, times(1)).findById(any(ContactId.class));
        verify(contactRepository, times(0)).save(any(Contact.class));
    }

    @Test
    void updateContactEmail() {
        when(contactRepository.findById(any(ContactId.class))).thenReturn(Optional.of(contact));
        when(contactRepository.save(any(Contact.class))).thenReturn(contact);

        Contact updatedContact = contactService.updateContactEmail("1", "max.mustermann@example.com");

        assertNotNull(updatedContact);
        assertEquals("max.mustermann@example.com", updatedContact.getEmail());
        verify(contactRepository, times(1)).findById(any(ContactId.class));
        verify(contactRepository, times(1)).save(any(Contact.class));
    }

    @Test
    void updateContactEmail_NotFound() {
        when(contactRepository.findById(any(ContactId.class))).thenReturn(Optional.empty());

        assertThrows(ContactNotFoundException.class, () -> contactService.updateContactEmail("1", "max.mustermann@example.com"));
        verify(contactRepository, times(1)).findById(any(ContactId.class));
        verify(contactRepository, times(0)).save(any(Contact.class));
    }

    @Test
    void updateContactPhoneNumber() {
        when(contactRepository.findById(any(ContactId.class))).thenReturn(Optional.of(contact));
        when(contactRepository.save(any(Contact.class))).thenReturn(contact);

        Contact updatedContact = contactService.updateContactPhoneNumber("1", "+123456789");

        assertNotNull(updatedContact);
        assertEquals("+123456789", updatedContact.getPhoneNumber());
        verify(contactRepository, times(1)).findById(any(ContactId.class));
        verify(contactRepository, times(1)).save(any(Contact.class));
    }

    @Test
    void updateContactPhoneNumber_NotFound() {
        when(contactRepository.findById(any(ContactId.class))).thenReturn(Optional.empty());

        assertThrows(ContactNotFoundException.class, () -> contactService.updateContactPhoneNumber("1", "+123456789"));
        verify(contactRepository, times(1)).findById(any(ContactId.class));
        verify(contactRepository, times(0)).save(any(Contact.class));
    }

    @Test
    void addSpecialDateToContact() {
        SpecialDate specialDate = new SpecialDate(new java.util.Date(), "Birthday");
        when(contactRepository.findById(any(ContactId.class))).thenReturn(Optional.of(contact));
        when(contactRepository.save(any(Contact.class))).thenReturn(contact);

        Contact updatedContact = contactService.addSpecialDateToContact("1", specialDate);

        assertNotNull(updatedContact);
        assertTrue(updatedContact.getSpecialDates().contains(specialDate));
        verify(contactRepository, times(1)).findById(any(ContactId.class));
        verify(contactRepository, times(1)).save(any(Contact.class));
    }

    @Test
    void addSpecialDateToContact_NotFound() {
        SpecialDate specialDate = new SpecialDate(new java.util.Date(), "Birthday");
        when(contactRepository.findById(any(ContactId.class))).thenReturn(Optional.empty());

        assertThrows(ContactNotFoundException.class, () -> contactService.addSpecialDateToContact("1", specialDate));
        verify(contactRepository, times(1)).findById(any(ContactId.class));
        verify(contactRepository, times(0)).save(any(Contact.class));
    }

    @Test
    void removeSpecialDateFromContact() {
        SpecialDate specialDate = new SpecialDate(new java.util.Date(), "Birthday");
        contact.addSpecialDate(specialDate);
        when(contactRepository.findById(any(ContactId.class))).thenReturn(Optional.of(contact));
        when(contactRepository.save(any(Contact.class))).thenReturn(contact);

        Contact updatedContact = contactService.removeSpecialDateFromContact("1", "Birthday");

        assertNotNull(updatedContact);
        assertFalse(updatedContact.getSpecialDates().contains(specialDate));
        verify(contactRepository, times(1)).findById(any(ContactId.class));
        verify(contactRepository, times(1)).save(any(Contact.class));
    }

    @Test
    void removeSpecialDateFromContact_NotFound() {
        when(contactRepository.findById(any(ContactId.class))).thenReturn(Optional.empty());

        assertThrows(ContactNotFoundException.class, () -> contactService.removeSpecialDateFromContact("1", "Birthday"));
        verify(contactRepository, times(1)).findById(any(ContactId.class));
        verify(contactRepository, times(0)).save(any(Contact.class));
    }

    @Test
    void findContactById() {
        when(contactRepository.findById(any(ContactId.class))).thenReturn(Optional.of(contact));

        Optional<Contact> foundContact = contactService.findContactById("1");

        assertTrue(foundContact.isPresent());
        assertEquals(contact, foundContact.get());
        verify(contactRepository, times(1)).findById(any(ContactId.class));
    }

    @Test
    void findContactById_NotFound() {
        when(contactRepository.findById(any(ContactId.class))).thenReturn(Optional.empty());

        Optional<Contact> foundContact = contactService.findContactById("1");

        assertFalse(foundContact.isPresent());
        verify(contactRepository, times(1)).findById(any(ContactId.class));
    }

    @Test
    void findContactByName() {
        when(contactRepository.findByName("Max")).thenReturn(Optional.of(List.of(contact)));

        Optional<List<Contact>> foundContacts = contactService.findContactByName("Max");

        assertTrue(foundContacts.isPresent());
        assertEquals(1, foundContacts.get().size());
        assertEquals(contact, foundContacts.get().get(0));
        verify(contactRepository, times(1)).findByName("Max");
    }

    @Test
    void findContactByName_NotFound() {
        when(contactRepository.findByName("Max")).thenReturn(Optional.empty());

        Optional<List<Contact>> foundContacts = contactService.findContactByName("Max");

        assertFalse(foundContacts.isPresent());
        verify(contactRepository, times(1)).findByName("Max");
    }

    @Test
    void findAllContacts() {
        when(contactRepository.getAllContacts()).thenReturn(List.of(contact));

        List<Contact> allContacts = contactService.findAllContacts();

        assertEquals(1, allContacts.size());
        assertEquals(contact, allContacts.get(0));
        verify(contactRepository, times(1)).getAllContacts();
    }

    @Test
    void deleteContactById() {
        doNothing().when(contactRepository).deleteContact(any(ContactId.class));
        when(contactRepository.findById(any(ContactId.class))).thenReturn(Optional.of(contact));

        contactService.deleteContactById("1");

        verify(contactRepository, times(1)).deleteContact(any(ContactId.class));
    }

    @Test
    void deleteContactById_NotFound() {
        when(contactRepository.findById(any(ContactId.class))).thenReturn(Optional.empty());

        assertThrows(ContactNotFoundException.class, () -> contactService.deleteContactById("1"));
        verify(contactRepository, times(1)).findById(any(ContactId.class));
        verify(contactRepository, times(0)).deleteContact(any(ContactId.class));
    }
}
