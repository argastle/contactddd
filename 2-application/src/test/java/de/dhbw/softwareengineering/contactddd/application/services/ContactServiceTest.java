package de.dhbw.softwareengineering.contactddd.application.services;

import de.dhbw.softwareengineering.contactddd.domain.entities.Contact;
import de.dhbw.softwareengineering.contactddd.domain.repositories.IContactRepository;
import de.dhbw.softwareengineering.contactddd.domain.values.ContactId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ContactServiceTest {

    @Mock
    private IContactRepository contactRepository;

    private ContactService contactService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        contactService = new ContactService(contactRepository);
    }

    @Test
    void createContactShouldPersistContact() {
        // Arrange
        CreateContactCommand command = new CreateContactCommand("Max Mustermann", "max@example.com", "1234567890", new HashSet<>());
        Contact contact = new Contact("Max Mustermann", "max.mustermann@gmx.de", "1234567890", new HashSet<>());
        when(contactRepository.save(any(Contact.class))).thenReturn(contact);

        // Act
        Contact createdContact = contactService.createContact(command);

        // Assert
        assertNotNull(createdContact);
        assertEquals("Max Mustermann", createdContact.getName());
        verify(contactRepository).save(any(Contact.class));
    }

    @Test
    void findContactByIdShouldReturnContact() {
        // Arrange
        Contact contact = new Contact("Max Mustermann", "max.mustermann@gmx.de", "1234567890", new HashSet<>());
        when(contactRepository.findById(any(ContactId.class))).thenReturn(Optional.of(contact));

        // Act
        Optional<Contact> foundContact = contactService.findContactById("123");

        // Assert
        assertTrue(foundContact.isPresent());
        assertEquals("Max Mustermann", foundContact.get().getName());
    }

    @Test
    void updateContactShouldModifyExistingContact() {
        // Arrange
        Contact originalContact = new Contact("Max Mustermann", "max@max.mustermann@gmx.de", "1234567890", new HashSet<>());
        when(contactRepository.findById(any(ContactId.class))).thenReturn(Optional.of(originalContact));
        when(contactRepository.save(any(Contact.class))).thenReturn(originalContact);

        CreateContactCommand updateCommand = new CreateContactCommand("Max Mustermann Updated", "max.mustermann_updated@gmx.de", "0987654321", new HashSet<>());

        // Act
        Contact updatedContact = contactService.updateContact("123", updateCommand);

        // Assert
        assertNotNull(updatedContact);
        assertEquals("Max Mustermann Updated", updatedContact.getName());
        verify(contactRepository).save(any(Contact.class));
    }

    @Test
    void deleteContactByIdShouldRemoveContact() {
        // Arrange
        doNothing().when(contactRepository).deleteContact(any(ContactId.class));

        // Act
        contactService.deleteContactById("123");

        // Assert
        verify(contactRepository).deleteContact(any(ContactId.class));
    }
}
